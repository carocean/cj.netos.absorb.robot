package cj.netos.absorb.robot;

import cj.netos.absorb.robot.model.HubTails;

import java.math.BigDecimal;
import java.util.List;

public interface IHubTailBillService {
    List<HubTails> pageBill(String wenyBankID, int limit, long offset);

    List<HubTails> getBillOfMonth(String wenyBankID, int year, int month, int limit, long offset);

    List<HubTails> pageBillOfMonth(String wenyBankID, int order, int year, int month, int limit, long offset);

    BigDecimal totalInBillOfMonth(String wenyBankID, int year, int month);

    BigDecimal totalInBillOfYear(String wenyBankID, int year);

    BigDecimal totalOutBillOfMonth(String wenyBankID, int year, int month);

    BigDecimal totalOutBillOfYear(String wenyBankID, int year);
}
