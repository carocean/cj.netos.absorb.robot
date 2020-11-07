package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IHubService;
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
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;

@CjBridge(aspects = "@transaction")
@CjService(name = "investService")
public class InvestService implements IInvestService {
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.InvestRecordMapper")
    InvestRecordMapper investRecordMapper;

    @CjServiceRef
    IHubService hubService;

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
        record.setPayStatus(result.getStatus());
        record.setPayMessage(result.getMessage());

        investRecordMapper.insert(record);

        if (result.getState() != 200) {//如果付款出错则不分发
            CJSystem.logging().error(getClass(), String.format("付款投单出错:%s %s", result.getStatus(), result.getMessage()));
            return;
        }
        IHubDistribute<InvestRecord> onInvestHubDistribute = new OnInvestHubDistribute(hubService, rabbitMQProducer);
        onInvestHubDistribute.distribute(record);
    }
}
