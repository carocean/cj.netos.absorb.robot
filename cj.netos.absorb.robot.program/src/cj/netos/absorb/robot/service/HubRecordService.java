package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IHubRecordService;
import cj.netos.absorb.robot.mapper.InvestRecordMapper;
import cj.netos.absorb.robot.mapper.RecipientsRecordMapper;
import cj.netos.absorb.robot.mapper.WithdrawRecordMapper;
import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.WithdrawRecord;
import cj.netos.absorb.robot.result.RecipientsRecordInfoResult;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;
import java.util.List;

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
    public RecipientsRecordInfoResult getRecipientsRecordInfo(String record_sn) {
        return recipientsRecordMapper.getRecipientsRecordInfo(record_sn);
    }

    @CjTransaction
    @Override
    public List<RecipientsRecord> pageRecipientsRecordByPerson(String absorberid, String recipients, int limit, long offset) {
        return recipientsRecordMapper.pageByPerson(absorberid, recipients, limit, offset);
    }

    @CjTransaction
    @Override
    public List<RecipientsRecord> pageRecipientsRecordById(String recipientsId, int limit, long offset) {
        return recipientsRecordMapper.pageByRecipientsId(recipientsId, limit, offset);
    }


    @CjTransaction
    @Override
    public List<RecipientsRecord> pageRecipientsRecordWhere(String absorberid, String recipientsId, int limit, long offset) {
        return recipientsRecordMapper.pageRecipientsWhere(absorberid, recipientsId, limit, offset);
    }

    @CjTransaction
    @Override
    public List<RecipientsRecord> pageRecipientsRecordWhere3(String absorber, String recipientsId, int year, int month, int limit, long offset) {
        return recipientsRecordMapper.pageRecipientsWhere3(absorber, recipientsId, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public List<RecipientsRecord> pageRecipientsRecordByOrderWhere(String absorber, String recipientsId, int order, int limit, long offset) {
        return recipientsRecordMapper.pageRecipientsRecordByOrderWhere(absorber, recipientsId, order, limit, offset);
    }

    @CjTransaction
    @Override
    public List<RecipientsRecord> pageRecipientsRecordByOrderWhere2(String absorber, String recipientsId, int order, int year, int month, int limit, long offset) {
        return recipientsRecordMapper.pageRecipientsRecordByOrderWhere2(absorber, recipientsId, order, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public BigDecimal totalRecipientsRecord(String absorber, String recipients) {
        return recipientsRecordMapper.totalRecipientsRecord(absorber, recipients);
    }

    @CjTransaction
    @Override
    public BigDecimal totalRecipientsRecordById(String recipientsId) {
        return recipientsRecordMapper.totalRecipientsRecordById(recipientsId);
    }

    @CjTransaction
    @Override
    public BigDecimal totalRecipientsRecordWhere(String absorberid, String recipientsId) {
        return recipientsRecordMapper.totalRecipientsRecordWhere(absorberid, recipientsId);
    }

    @CjTransaction
    @Override
    public BigDecimal totalRecipientsRecordByOrderWhere(String absorberid, String recipientsId, int order) {
        return recipientsRecordMapper.totalRecipientsRecordByOrderWhere(absorberid, recipientsId, order);
    }

    @CjTransaction
    @Override
    public BigDecimal totalRecipientsRecordByOrderWhere2(String absorberid, String recipientsId, int order, int year, int month) {

        return recipientsRecordMapper.totalRecipientsRecordByOrderWhere2(absorberid, recipientsId, order, year, month);
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

    @CjTransaction
    @Override
    public List<InvestRecord> pageInvestRecord(String absorber, int limit, long offset) {
        return investRecordMapper.pageInvestRecord(absorber, limit, offset);
    }

    @CjTransaction
    @Override
    public long totalAmountInvests(String absorber) {
        return investRecordMapper.totalAmountInvests(absorber);
    }

    @CjTransaction
    @Override
    public List<WithdrawRecord> pageWithdrawRecord(String bankid, int limit, long offset) {
        return withdrawRecordMapper.pageWithdrawRecord(bankid, limit, offset);
    }

    @CjTransaction
    @Override
    public long totalAmountWithdraws(String bankid) {
        return withdrawRecordMapper.totalAmountWithdraws(bankid);
    }
}
