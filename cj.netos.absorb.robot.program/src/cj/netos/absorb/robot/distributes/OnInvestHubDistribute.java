package cj.netos.absorb.robot.distributes;

import cj.netos.absorb.robot.IAbsorberDistribute;
import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.IHubDistribute;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.net.CircuitException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OnInvestHubDistribute implements IHubDistribute<InvestRecord> {
    IAbsorberHubService absorberHubService;
    IRabbitMQProducer rabbitMQProducer;

    public OnInvestHubDistribute(IAbsorberHubService absorberHubService, IRabbitMQProducer rabbitMQProducer) {
        this.absorberHubService = absorberHubService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public void distribute(InvestRecord result) throws CircuitException {
        Absorber absorber = absorberHubService.getAbsorber(result.getAbsorber());
        IAbsorberDistribute absorberDistribute = new AbsorberDistribute(this.absorberHubService, rabbitMQProducer);

        BigDecimal realDistributeAmount = new BigDecimal("0.00");
        //每权的价格
        BigDecimal weightPricePerAbsorber=new BigDecimal(result.getAmount()+"").divide(absorber.getWeight(),8,RoundingMode.HALF_DOWN);
        BigDecimal distributedAmount = absorberDistribute.distribute(absorber, weightPricePerAbsorber, result);
        realDistributeAmount=realDistributeAmount.add(distributedAmount);

        BigDecimal remainingAmount = new BigDecimal(result.getAmount() + "").subtract(realDistributeAmount);
        if (remainingAmount.compareTo(new BigDecimal("0.00")) > 0) {
            //尾金存入hub的余额上，将来平台可以提出来
            absorberHubService.addTailAmount(remainingAmount,absorber.getBankid(),result.getSn(),1, "尾金");
        }
    }
}
