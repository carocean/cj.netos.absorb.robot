package cj.netos.absorb.robot;

import cj.netos.absorb.robot.model.HubTails;

import java.util.List;

public interface IHubTailBillService {
    List<HubTails> pageBill(String wenyBankID, int limit, long offset);

    List<HubTails> getBillOfMonth(String wenyBankID, int year, int month, int limit, long offset);

    List<HubTails> pageBillOfMonth(String wenyBankID, int order, int year, int month, int limit, long offset);

    long totalInBillOfMonth(String wenyBankID, int year, int month);

    long totalInBillOfYear(String wenyBankID, int year);

    long totalOutBillOfMonth(String wenyBankID, int year, int month);

    long totalOutBillOfYear(String wenyBankID, int year);
}
