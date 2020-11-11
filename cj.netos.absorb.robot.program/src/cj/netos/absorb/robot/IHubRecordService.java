package cj.netos.absorb.robot;

import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.WithdrawRecord;
import cj.netos.absorb.robot.result.RecipientsRecordInfoResult;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface IHubRecordService {
    RecipientsRecord getRecipientsRecord(String record_sn);

    InvestRecord getInvestRecord(String record_sn);

    WithdrawRecord getWithdrawRecord(String record_sn);

    List<RecipientsRecord> pageRecipientsRecordWhere(String absorberid, String recipientsId, int limit, long offset);

    BigDecimal totalRecipientsRecord(String absorber, String recipients);

    BigDecimal totalRecipientsRecordById(String recipientsId);

    List<RecipientsRecord> pageRecipientsRecordByPerson(String absorberid, String recipients, int limit, long offset);

    List<RecipientsRecord> pageRecipientsRecordById(String recipientsId, int limit, long offset);

    List<InvestRecord> pageInvestRecord(String absorber, int limit, long offset);

    long totalAmountInvests(String absorber);

    List<WithdrawRecord> pageWithdrawRecord(String bankid, int limit, long offset);

    long totalAmountWithdraws(String bankid);

    BigDecimal totalRecipientsRecordWhere(String absorberid, String recipientsId);

    List<RecipientsRecord> pageRecipientsRecordByOrderWhere(String absorber, String recipientsId, int order, int limit, long offset);

    BigDecimal totalRecipientsRecordByOrderWhere(String absorberid, String recipientsId, int order);

    List<RecipientsRecord> pageRecipientsRecordByOrderWhere2(String absorber, String recipientsId, int order, int year, int month, int limit, long offset);

    BigDecimal totalRecipientsRecordByOrderWhere2(String absorberid, String recipientsId, int order, int year, int month);

    List<RecipientsRecord> pageRecipientsRecordWhere3(String absorber, String recipientsId, int year, int month, int limit, long offset);

    RecipientsRecordInfoResult getRecipientsRecordInfo(String record_sn);

}
