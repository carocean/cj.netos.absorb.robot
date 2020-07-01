package cj.netos.absorb.robot.distributes;

import cj.netos.absorb.robot.GeoReceptorBO;
import cj.netos.absorb.robot.IAbsorberDistribute;
import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.POR;
import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.bo.RecipientsAbsorbBill;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.Recipients;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbsorberDistribute implements IAbsorberDistribute {
    IAbsorberHubService absorberHubService;

    IRabbitMQProducer rabbitMQProducer;
    int _limit_simple = 500;
    long _offset_simple = 0;

    public AbsorberDistribute(IAbsorberHubService absorberHubService, IRabbitMQProducer rabbitMQProducer) {
        this.absorberHubService = absorberHubService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    //返回实际分出去的钱数
    @Override
    public BigDecimal distribute(Absorber absorber, BigDecimal weightPricePerAbsorber, Object result) throws CircuitException {
        switch (absorber.getType()) {
            case 0://简单洇取器
                return distributeSimpleAbsorber(absorberHubService, absorber, weightPricePerAbsorber, result);
            case 1://地理洇取器
                return distributeGeoAbsorber(absorberHubService, absorber, weightPricePerAbsorber, result);
            default:
                throw new EcmException("不支持的洇取器类型:" + absorber.getType());
        }

    }

    private BigDecimal distributeGeoAbsorber(IAbsorberHubService absorberHubService, Absorber absorber, BigDecimal weightPricePerAbsorber, Object result) throws CircuitException {
        //这个洇取器可发的钱
        BigDecimal absorberAmount = weightPricePerAbsorber.multiply(absorber.getWeight());
        BigDecimal realDistributeAmount = new BigDecimal("0.00");
        //地理洇取器由于是动态取收取人的，所以应根据洇取器设定的人数上限获取，否则人数太多
        long maxRecipientCount = absorberHubService.getMaxRecipientsCount(absorber);
        List<POR> porList = absorberHubService.searchAroundPerson(absorber.getLocation(), absorber.getRadius(), (int) maxRecipientCount, 0);
        if (porList.isEmpty()) {
            return realDistributeAmount;
        }
        //求权和，把半径-离中心的距离加起来作为权和
        List<Recipients> recipientsList=new ArrayList<>();
        BigDecimal totalWeightsOfRecipients = new BigDecimal("0.00");
        for (POR por : porList) {
            BigDecimal weight=new BigDecimal(absorber.getRadius() + "").subtract(por.getDistance());//离中心越近权重越大，故而半径减之
            //越近权重越高,当离圈后就取消洇金了
            GeoReceptorBO bo = por.getReceptor();
            Recipients recipients = new Recipients();
            recipients.setId(bo.getId());
            recipients.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
            recipients.setPerson(bo.getCreator());
            recipients.setPersonName(bo.getTitle());
            recipients.setEncourageCode("enter");
            recipients.setEncourageCause("进圈");
            recipients.setDesireAmount(0L);
            recipients.setAbsorber(absorber.getId());
            recipients.setWeight(weight);//距中心位置作为权重
            recipientsList.add(recipients);

            totalWeightsOfRecipients = totalWeightsOfRecipients.add(weight);
        }
        if (totalWeightsOfRecipients.compareTo(realDistributeAmount) == 0) {//如果权重和为0则退出处理
            return realDistributeAmount;
        }
        //求每权的价格
        BigDecimal weightPricePerRecipients = absorberAmount.divide(totalWeightsOfRecipients, 8, RoundingMode.HALF_DOWN);
        //每个收取人权重*价格即是要发的钱

        for (Recipients recipients : recipientsList) {
            BigDecimal money = weightPricePerRecipients.multiply(recipients.getWeight());
            //如果为0则跳过
            if (new BigDecimal("0.00").compareTo(money) == 0) {
                continue;
            }
            realDistributeAmount.add(money);
            //生成并存储收取记录单并生成收取账单并将其提交给mhub，钱包会侦听收取单，并存入到收取人的洇金账户
            transToWallet(absorber, recipients, result, money);
        }
        return realDistributeAmount;
    }

    private BigDecimal distributeSimpleAbsorber(IAbsorberHubService absorberHubService, Absorber absorber, BigDecimal weightPricePerAbsorber, Object result) throws CircuitException {

        //这个洇取器可发的钱
        BigDecimal absorberAmount = weightPricePerAbsorber.multiply(absorber.getWeight());
        BigDecimal totalWeightsOfRecipients = absorberHubService.totalWeightsOfRecipients(absorber.getId());
        BigDecimal realDistributeAmount = new BigDecimal("0.00");
        if (totalWeightsOfRecipients.compareTo(realDistributeAmount) == 0) {//如果权重和为0则退出处理
            return realDistributeAmount;
        }
        //求每权的价格
        BigDecimal weightPricePerRecipients = absorberAmount.divide(totalWeightsOfRecipients, 8, RoundingMode.HALF_DOWN);
        //每个收取人权重*价格即是要发的钱
        while (true) {
            List<Recipients> recipientsList = absorberHubService.pageRecipients(absorber.getId(), _limit_simple, _offset_simple);
            if (recipientsList.isEmpty()) {
                break;
            }
            for (Recipients recipients : recipientsList) {
                //收取人可以得到的钱
                BigDecimal money = weightPricePerRecipients.multiply(recipients.getWeight());
                //如果为0则跳过
                if (new BigDecimal("0.00").compareTo(money) == 0) {
                    continue;
                }
                realDistributeAmount.add(money);
                //生成并存储收取记录单并生成收取账单并将其提交给mhub，钱包会侦听收取单，并存入到收取人的洇金账户
                transToWallet(absorber, recipients, result, money);
            }
            _offset_simple += recipientsList.size();
        }
        return realDistributeAmount;
    }

    private void transToWallet(Absorber absorber, Recipients recipients, Object result, BigDecimal money) throws CircuitException {
        RecipientsAbsorbBill bill = absorberHubService.addRecipientsRecord(absorber, recipients, result, money);
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
