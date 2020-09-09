package cj.netos.absorb.robot.distributes;

import cj.netos.absorb.robot.IAbsorberDistribute;
import cj.netos.absorb.robot.IHubService;
import cj.netos.absorb.robot.IHubDistribute;
import cj.netos.absorb.robot.bo.DomainBulletin;
import cj.netos.absorb.robot.model.AbsorberBucket;
import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.net.CircuitException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OnInvestHubDistribute implements IHubDistribute<InvestRecord> {
    IHubService absorberHubService;
    IRabbitMQProducer rabbitMQProducer;

    public OnInvestHubDistribute(IHubService absorberHubService, IRabbitMQProducer rabbitMQProducer) {
        this.absorberHubService = absorberHubService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public void distribute(InvestRecord result) throws CircuitException {
        //投资都是投向一个指定的洇取器的
        AbsorberBucket bucket = absorberHubService.getAndInitAbsorbBucket(result.getAbsorber());
        //纹银银行的洇金按权重发给每个收取人，除非其权重要发的钱不够1分的八位小数。
        //总权重=本次统计符合条件(洇取器状态为运行中且>=加权平均价)的洇取器的价格总和
        //在创世时指数为洇取器个数，此时假设每个洇取器的价格是1，这样的结果是使得在创世时每个洇取器分得一样的钱，这样就保持了算法的一致性
        DomainBulletin bulletin = absorberHubService.getDomainBulletin(bucket.getBank());
        BigDecimal totalWeightsOfAbsorber = bulletin.getAbsorbWeights();
        if (totalWeightsOfAbsorber.compareTo(BigDecimal.ZERO) == 0) {
            //尾金存入hub的余额上，将来平台可以提出来
            absorberHubService.addTailAmount(new BigDecimal(result.getAmount() + ""), result.getInvester(), bucket.getBank(), result.getOutTradeSn(), 0, "Hub为空");
            return;
        }
        //求每权的价格
        //总权重=本次统计符合条件(洇取器状态为运行中且>=加权平均价)的洇取器的价格总和
        //因此每权价=待发洇金/总权重
        //而后每个洇取器的应发洇金为=洇取器价格*每权价，从权重的高低次序发放，直到发完为止
        BigDecimal weightPricePerAbsorber = new BigDecimal(result.getAmount() + "").divide(totalWeightsOfAbsorber, RobotUtils.BIGDECIMAL_SCALE, RoundingMode.HALF_DOWN);
        //地理洇取器全部优先
        BigDecimal realDistributeAmount = BigDecimal.ZERO;

        IAbsorberDistribute absorberDistribute = new AbsorberDistribute(this.absorberHubService, rabbitMQProducer);
        BigDecimal distributedAmount = absorberDistribute.distribute(bulletin, bucket, weightPricePerAbsorber, result);
        realDistributeAmount = realDistributeAmount.add(distributedAmount);

        BigDecimal remainingAmount = new BigDecimal(result.getAmount() + "").subtract(realDistributeAmount);
        if (remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            //尾金存入hub的余额上，将来平台可以提出来
            absorberHubService.addTailAmount(remainingAmount, result.getInvester(), bucket.getBank(), result.getOutTradeSn(), 0, "尾金");
        }
    }
}
