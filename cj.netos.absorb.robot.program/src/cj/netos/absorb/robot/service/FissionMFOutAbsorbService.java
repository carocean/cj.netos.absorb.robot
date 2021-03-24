package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.BankWithdrawResult;
import cj.netos.absorb.robot.IFissionMFOutAbsorbService;
import cj.netos.absorb.robot.IHubDistribute;
import cj.netos.absorb.robot.IHubService;
import cj.netos.absorb.robot.distributes.OnWithdrawHubDistribute;
import cj.netos.absorb.robot.mapper.WithdrawRecordMapper;
import cj.netos.absorb.robot.model.WithdrawRecord;
import cj.netos.absorb.robot.util.IdWorker;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

@CjBridge(aspects = "@transaction")
@CjService(name = "fissionMFOutAbsorbService")
public class FissionMFOutAbsorbService implements IFissionMFOutAbsorbService {
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.WithdrawRecordMapper")
    WithdrawRecordMapper withdrawRecordMapper;
    @CjServiceRef
    IHubService hubService;

    @CjServiceRef(refByName = "@.rabbitmq.producer.distributeAbsorbsToWallet")
    IRabbitMQProducer rabbitMQProducer;

    @CjTransaction
    @Override
    public void receive(BankWithdrawResult result) throws CircuitException {
        WithdrawRecord record = new WithdrawRecord();
        record.setAlias("网络洇金");
        record.setBankid(result.getBankid());
        record.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setReqAmount(result.getReqAmount());
        record.setRealAmount(result.getRealAmount());
        record.setShunter(result.getShunter());
        record.setSn(new IdWorker().nextId());
        record.setStatus("200");
        record.setMessage("ok");
        record.setPersonName(result.getPersonName());
        record.setWithdrawer(result.getWithdrawer());
        record.setCbtime(result.getDtime());
        record.setState(1);
        record.setRefsn(result.getOutTradeSn());
        withdrawRecordMapper.insert(record);

        IHubDistribute<BankWithdrawResult> onWithdrawDistributeService = new OnWithdrawHubDistribute(hubService, rabbitMQProducer);
        onWithdrawDistributeService.distribute(result);
    }
}
