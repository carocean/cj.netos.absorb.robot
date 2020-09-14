package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.IHubRecordService;
import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.WithdrawRecord;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.math.BigDecimal;
import java.util.List;

@CjService(name = "/record.ports")
public class HubRecordPorts implements IHubRecordPorts {
    @CjServiceRef
    IHubRecordService hubRecordService;

    @Override
    public RecipientsRecord getRecipientsRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return hubRecordService.getRecipientsRecord(record_sn);
    }

    @Override
    public List<RecipientsRecord> pageRecipientsRecordByPerson(ISecuritySession securitySession, String absorberid, String recipients, int limit, long offset) throws CircuitException {
        return hubRecordService.pageRecipientsRecordByPerson(absorberid, recipients, limit, offset);
    }

    @Override
    public List<RecipientsRecord> pageRecipientsRecordById(ISecuritySession securitySession, String recipientsId, int limit, long offset) throws CircuitException {
        return hubRecordService.pageRecipientsRecordById(recipientsId, limit, offset);
    }
    @Override
    public List<RecipientsRecord> pageRecipientsRecordWhere(ISecuritySession securitySession, String absorberid, String recipientsId, int limit, long offset) throws CircuitException {
        return hubRecordService.pageRecipientsRecordWhere(absorberid,recipientsId, limit, offset);
    }

    @Override
    public List<RecipientsRecord> pageRecipientsRecordByOrderWhere(ISecuritySession securitySession, String absorber, String recipientsId, int order, int limit, long offset) throws CircuitException {
        return hubRecordService.pageRecipientsRecordByOrderWhere(absorber,recipientsId,order, limit, offset);
    }

    @Override
    public BigDecimal totalRecipientsRecord(ISecuritySession securitySession, String absorber, String recipients) throws CircuitException {
        return hubRecordService.totalRecipientsRecord(absorber, recipients);
    }

    @Override
    public BigDecimal totalRecipientsRecordById(ISecuritySession securitySession, String recipientsId) throws CircuitException {
        return hubRecordService.totalRecipientsRecordById(recipientsId);
    }

    @Override
    public BigDecimal totalRecipientsRecordWhere(ISecuritySession securitySession, String absorberid, String recipientsId) throws CircuitException {
        return hubRecordService.totalRecipientsRecordWhere(absorberid,recipientsId);
    }

    @Override
    public BigDecimal totalRecipientsRecordByOrderWhere(ISecuritySession securitySession, String absorberid, String recipientsId, int order) throws CircuitException {
        return hubRecordService.totalRecipientsRecordByOrderWhere(absorberid,recipientsId,order);
    }

    @Override
    public InvestRecord getInvestRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return hubRecordService.getInvestRecord(record_sn);
    }

    @Override
    public WithdrawRecord getWithdrawRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return hubRecordService.getWithdrawRecord(record_sn);
    }

    @Override
    public List<InvestRecord> pageInvestRecord(ISecuritySession securitySession, String absorber, int limit, long offset) throws CircuitException {
        return hubRecordService.pageInvestRecord(absorber, limit, offset);
    }

    @Override
    public long totalAmountInvests(ISecuritySession securitySession, String absorber) throws CircuitException {
        return hubRecordService.totalAmountInvests(absorber);
    }

    @Override
    public List<WithdrawRecord> pageWithdrawRecord(ISecuritySession securitySession, String bankid, int limit, long offset) throws CircuitException {
        return hubRecordService.pageWithdrawRecord(bankid, limit, offset);
    }

    @Override
    public long totalAmountWithdraws(ISecuritySession securitySession, String bankid) throws CircuitException {
        return hubRecordService.totalAmountWithdraws(bankid);
    }
}
