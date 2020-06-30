package cj.netos.absorb.robot.distributes;

import cj.netos.absorb.robot.BankWithdrawResult;
import cj.netos.absorb.robot.IAbsorberDistribute;
import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.IHubDistribute;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.net.CircuitException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OnWithdrawHubDistribute implements IHubDistribute<BankWithdrawResult> {
    int _limit = 500;
    long _offset = 0;
    IAbsorberHubService absorberHubService;
    IRabbitMQProducer rabbitMQProducer;

    public OnWithdrawHubDistribute(IAbsorberHubService absorberHubService, IRabbitMQProducer rabbitMQProducer) {
        this.absorberHubService = absorberHubService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    //派发洇金，派发来自于纹银银行的洇金
    @Override
    public void distribute(BankWithdrawResult result) throws CircuitException {
        //纹银银行的洇金按权重发给每个收取人，除非其权重要发的钱不够1分的八位小数。而洇取器投资派发的采用随机抽样算法并不发给每个洇取器及期每个收取人。
        BigDecimal totalWeightsOfAbsorber = absorberHubService.totalWeightsOfAbsorber(result.getBankid());
        if (totalWeightsOfAbsorber.compareTo(new BigDecimal("0.00")) == 0) {//如果权重和为0则退出处理
            //尾金存入hub的余额上，将来平台可以提出来
            absorberHubService.addTailAmount(new BigDecimal(result.getRealAmount() + ""), result, "Hub为空");
            return;
        }
        //求每权的价格
        BigDecimal weightPricePerAbsorber = new BigDecimal(result.getRealAmount() + "").divide(totalWeightsOfAbsorber, 8, RoundingMode.HALF_DOWN);
        //地理洇取器全部优先
        BigDecimal realDistributeAmount = new BigDecimal("0.00");
        List<Absorber> geoAbsorbers = absorberHubService.pageAbsorber(result.getBankid(), 2, 0, Long.MAX_VALUE);
        for (Absorber absorber : geoAbsorbers) {
            IAbsorberDistribute absorberDistribute = new AbsorberDistribute(this.absorberHubService, rabbitMQProducer);
            BigDecimal distributedAmount = absorberDistribute.distribute(absorber, weightPricePerAbsorber, result);
            realDistributeAmount.add(distributedAmount);
        }
        while (true) {
            List<Absorber> simpleAbsorbers = absorberHubService.pageAbsorber(result.getBankid(), 1, _limit, _offset);
            if (simpleAbsorbers.isEmpty()) {
                break;
            }
            for (Absorber absorber : simpleAbsorbers) {
                IAbsorberDistribute absorberDistribute = new AbsorberDistribute(this.absorberHubService, rabbitMQProducer);
                BigDecimal distributedAmount = absorberDistribute.distribute(absorber, weightPricePerAbsorber, result);
                realDistributeAmount.add(distributedAmount);
            }
            _offset += simpleAbsorbers.size();
        }
        BigDecimal remainingAmount = new BigDecimal(result.getRealAmount() + "").subtract(realDistributeAmount);
        if (remainingAmount.compareTo(new BigDecimal("0.00")) > 0) {
            //尾金存入hub的余额上，将来平台可以提出来
            absorberHubService.addTailAmount(remainingAmount, result, "尾金");
        }
    }
}
