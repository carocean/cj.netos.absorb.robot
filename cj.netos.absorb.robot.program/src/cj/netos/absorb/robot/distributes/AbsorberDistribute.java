package cj.netos.absorb.robot.distributes;

import cj.netos.absorb.robot.*;
import cj.netos.absorb.robot.bo.DomainBulletin;
import cj.netos.absorb.robot.bo.RecipientsAbsorbBill;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.AbsorberBucket;
import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.Recipients;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbsorberDistribute implements IAbsorberDistribute {
    IHubService absorberHubService;

    IRabbitMQProducer rabbitMQProducer;
    int _limit_simple = 500;
    long _offset_simple = 0;

    public AbsorberDistribute(IHubService absorberHubService, IRabbitMQProducer rabbitMQProducer) {
        this.absorberHubService = absorberHubService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    //返回实际分出去的钱数
    @Override
    public BigDecimal distribute(DomainBulletin bulletin, AbsorberBucket bucket, BigDecimal weightPricePerAbsorber, Object result) throws CircuitException {
        Absorber absorber = absorberHubService.getAbsorber(bucket.getAbsorber());
        BigDecimal realDistribute = null;
        switch (absorber.getType()) {
            case 0://简单洇取器
                realDistribute = distributeSimpleAbsorber(absorberHubService, bucket, weightPricePerAbsorber, result);
                break;
            case 1://地理洇取器
                realDistribute = distributeGeoAbsorber(absorberHubService, absorber, bucket, weightPricePerAbsorber, result);
                break;
            default:
                throw new EcmException("不支持的洇取器类型:" + absorber.getType());
        }
        if (realDistribute.compareTo(BigDecimal.ZERO) <= 0) {
            return realDistribute;
        }
        if (result instanceof BankWithdrawResult) {
            absorberHubService.updateAbsorbBucket0(bulletin, bucket, realDistribute, (BankWithdrawResult) result);
        }
        if (result instanceof InvestRecord) {
            absorberHubService.updateByPersonInvest(bulletin, bucket, realDistribute, (InvestRecord) result);
        }
        return realDistribute;
    }

    private BigDecimal distributeGeoAbsorber(IHubService absorberHubService, Absorber absorber, AbsorberBucket bucket, BigDecimal weightPricePerAbsorber, Object result) throws CircuitException {
        //这个洇取器可发的钱，乘数尽量小，绝不能舍入加1的情况
        BigDecimal price = bucket.getPrice();
        if (price == null || BigDecimal.ZERO.compareTo(price) == 0) {
            price = new BigDecimal((1 / 0.001) + "");
        }
        BigDecimal absorberAmount = weightPricePerAbsorber.multiply(price).setScale(RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);
        BigDecimal realDistributeAmount = BigDecimal.ZERO;
        //地理洇取器由于是动态取收取人的，所以应根据洇取器设定的人数上限获取，否则人数太多
        long maxRecipientCount = absorberHubService.getMaxRecipientsCount(absorber);
        if (maxRecipientCount == 0) {
            maxRecipientCount = Integer.MAX_VALUE;
        }
        List<POR> porList = absorberHubService.searchAroundPerson(absorber.getLocation(), absorber.getRadius(), (int) maxRecipientCount, 0);
        if (porList.isEmpty()) {
            return realDistributeAmount;
        }
        //求权和，把半径-离中心的距离加起来作为权和
        List<Recipients> recipientsList = new ArrayList<>();
        BigDecimal totalWeightsOfRecipients = BigDecimal.ZERO;
        for (POR por : porList) {
            BigDecimal weight = new BigDecimal(absorber.getRadius() + "").subtract(por.getDistance());//离中心越近权重越大，故而半径减之
            //越近权重越高,当离圈后就取消洇金了
            GeoReceptorBO bo = por.getReceptor();
            Recipients recipients = new Recipients();
            recipients.setId(bo.getId());
            recipients.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
            recipients.setPerson(bo.getCreator());
            recipients.setPersonName(bo.getTitle());
            recipients.setEncourageCode("enter");
            recipients.setEncourageCause("在圈内");
            recipients.setDesireAmount(0L);
            recipients.setAbsorber(bucket.getAbsorber());
            recipients.setWeight(weight);//距中心位置作为权重
            recipientsList.add(recipients);

            totalWeightsOfRecipients = totalWeightsOfRecipients.add(weight);
        }
        if (totalWeightsOfRecipients.compareTo(realDistributeAmount) == 0) {//如果权重和为0则退出处理
            return realDistributeAmount;
        }
        //求每权的价格,每权价必须小，不然每个收取人一乘就多了，尾金会起出实际要发的金额
        BigDecimal weightPricePerRecipients = absorberAmount.divide(totalWeightsOfRecipients, RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);
        //每个收取人权重*价格即是要发的钱

        for (Recipients recipients : recipientsList) {
            BigDecimal money = weightPricePerRecipients.multiply(recipients.getWeight()).setScale(RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);
            //如果为0则跳过
            if (new BigDecimal("0.00").compareTo(money) == 0) {
                continue;
            }
            realDistributeAmount = realDistributeAmount.add(money);
            //生成并存储收取记录单并生成收取账单并将其提交给mhub，钱包会侦听收取单，并存入到收取人的洇金账户
            transToWallet(bucket, recipients, result, money);
        }
        return realDistributeAmount;
    }

    private BigDecimal distributeSimpleAbsorber(IHubService absorberHubService, AbsorberBucket bucket, BigDecimal weightPricePerAbsorber, Object result) throws CircuitException {

        //这个洇取器可发的钱，乘数尽量小，绝不能舍入加1的情况
        BigDecimal price = bucket.getPrice();
        if (BigDecimal.ZERO.compareTo(price) == 0) {
            price = new BigDecimal((1 / 0.001) + "");
        }
        BigDecimal absorberAmount = weightPricePerAbsorber.multiply(price).setScale(RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);
        BigDecimal totalWeightsOfRecipients = absorberHubService.totalWeightsOfRecipients(bucket.getAbsorber());
        BigDecimal realDistributeAmount = BigDecimal.ZERO;
        if (totalWeightsOfRecipients.compareTo(BigDecimal.ZERO) == 0) {//如果权重和为0则退出处理
            return realDistributeAmount;
        }
        //求每权的价格,每权价必须小，不然每个收取人一乘就多了，尾金会起出实际要发的金额
        BigDecimal weightPricePerRecipients = absorberAmount.divide(totalWeightsOfRecipients, RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);
        //每个收取人权重*价格即是要发的钱
        while (true) {
            List<Recipients> recipientsList = absorberHubService.pageRecipients(bucket.getAbsorber(), _limit_simple, _offset_simple);
            if (recipientsList.isEmpty()) {
                break;
            }
            for (Recipients recipients : recipientsList) {
                //收取人可以得到的钱
                BigDecimal money = weightPricePerRecipients.multiply(recipients.getWeight()).setScale(RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);
                //如果为0则跳过
                if (new BigDecimal("0.00").compareTo(money) == 0) {
                    continue;
                }
                realDistributeAmount = realDistributeAmount.add(money);
                //生成并存储收取记录单并生成收取账单并将其提交给mhub，钱包会侦听收取单，并存入到收取人的洇金账户
                transToWallet(bucket, recipients, result, money);
            }
            _offset_simple += recipientsList.size();
        }
        return realDistributeAmount;
    }

    private void transToWallet(AbsorberBucket bucket, Recipients recipients, Object result, BigDecimal money) throws CircuitException {
        RecipientsAbsorbBill bill = absorberHubService.addRecipientsRecord(bucket, recipients, result, money);
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/robot/hub.ports")
                .headers(new HashMap() {
                    {
                        put("command", "distribute");
                    }
                }).build();
        byte[] body = new Gson().toJson(bill).getBytes();
        rabbitMQProducer.publish("wallet", properties, body);
    }
}
