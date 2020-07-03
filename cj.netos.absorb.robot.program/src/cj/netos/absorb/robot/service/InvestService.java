package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.IHubDistribute;
import cj.netos.absorb.robot.IInvestService;
import cj.netos.absorb.robot.PaymentResult;
import cj.netos.absorb.robot.distributes.OnInvestHubDistribute;
import cj.netos.absorb.robot.mapper.InvestRecordMapper;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.util.IdWorker;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

@CjBridge(aspects = "@transaction")
@CjService(name = "investService")
public class InvestService implements IInvestService {
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.InvestRecordMapper")
    InvestRecordMapper investRecordMapper;

    @CjServiceRef
    IAbsorberHubService absorberHubService;

    @CjServiceRef(refByName = "@.rabbitmq.producer.distributeAbsorbsToWallet")
    IRabbitMQProducer rabbitMQProducer;

    @CjTransaction
    @Override
    public void doResponse(Absorber absorber, PaymentResult result) throws CircuitException {
        if (result.getAmount() <= 0) {
            throw new CircuitException("500", String.format("投单金额小于等于0"));
        }
        InvestRecord record = new InvestRecord();
        record.setAbsorber(absorber.getId());
        record.setAmount(result.getAmount());
        record.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setInvester(result.getPerson());
        record.setPersonName(result.getPersonName());
        record.setOutTradeSn(result.getSn());
        record.setSn(new IdWorker().nextId());
        record.setNote(result.getNote());
        record.setInvestOrderNo(result.getDetails().getOrderNo());
        record.setInvestOrderTitle(result.getDetails().getOrderTitle());
        investRecordMapper.insert(record);
        IHubDistribute<InvestRecord> onInvestHubDistribute = new OnInvestHubDistribute(absorberHubService, rabbitMQProducer);
        onInvestHubDistribute.distribute(record);
    }
}