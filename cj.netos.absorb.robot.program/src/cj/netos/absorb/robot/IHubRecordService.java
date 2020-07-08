package cj.netos.absorb.robot;

import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.WithdrawRecord;

import java.math.BigDecimal;

public interface IHubRecordService {
    RecipientsRecord getRecipientsRecord(String record_sn);

    InvestRecord getInvestRecord(String record_sn);

    WithdrawRecord getWithdrawRecord(String record_sn);

    BigDecimal totalRecipientsRecord(String absorber, String recipients);

    BigDecimal totalRecipientsRecordById(String recipientsId);

}
