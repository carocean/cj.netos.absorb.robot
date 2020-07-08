package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IHubRecordService;
import cj.netos.absorb.robot.mapper.InvestRecordMapper;
import cj.netos.absorb.robot.mapper.RecipientsRecordMapper;
import cj.netos.absorb.robot.mapper.WithdrawRecordMapper;
import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.WithdrawRecord;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;

@CjBridge(aspects = "@transaction")
@CjService(name = "hubRecordService")
public class HubRecordService implements IHubRecordService {
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.RecipientsRecordMapper")
    RecipientsRecordMapper recipientsRecordMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.InvestRecordMapper")
    InvestRecordMapper investRecordMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.WithdrawRecordMapper")
    WithdrawRecordMapper withdrawRecordMapper;

    @CjTransaction
    @Override
    public RecipientsRecord getRecipientsRecord(String record_sn) {
        return recipientsRecordMapper.selectByPrimaryKey(record_sn);
    }

    @CjTransaction
    @Override
    public BigDecimal totalRecipientsRecord(String absorber, String recipients) {
        return recipientsRecordMapper.totalRecipientsRecord(absorber,recipients);
    }

    @CjTransaction
    @Override
    public InvestRecord getInvestRecord(String record_sn) {
        return investRecordMapper.selectByPrimaryKey(record_sn);
    }

    @CjTransaction
    @Override
    public WithdrawRecord getWithdrawRecord(String record_sn) {
        return withdrawRecordMapper.selectByPrimaryKey(record_sn);
    }
}
