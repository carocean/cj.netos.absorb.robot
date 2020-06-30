package cj.netos.absorb.robot.distributes;

import cj.netos.absorb.robot.IAbsorberDistribute;
import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.bo.RecipientsAbsorbBill;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.Recipients;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

public class AbsorberDistribute implements IAbsorberDistribute {
    int _limit = 500;
    long _offset = 0;
    IAbsorberHubService absorberHubService;

    IRabbitMQProducer rabbitMQProducer;

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

    private BigDecimal distributeGeoAbsorber(IAbsorberHubService absorberHubService, Absorber absorber, BigDecimal weightPricePerAbsorber, Object result) {
        return new BigDecimal("0.00");
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
            List<Recipients> recipientsList = absorberHubService.pageRecipients(absorber.getId(), _limit, _offset);
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
            _offset += recipientsList.size();
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
