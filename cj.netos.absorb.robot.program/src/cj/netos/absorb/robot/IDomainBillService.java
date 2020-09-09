package cj.netos.absorb.robot;

import cj.netos.absorb.robot.model.DomainBill;
import cj.netos.absorb.robot.model.HubTails;

import java.util.List;

public interface IDomainBillService {

    List<DomainBill> pageBill(String wenyBankID, int limit, long offset);

    List<DomainBill> getBillOfMonth(String wenyBankID, int year, int month, int limit, long offset);

    List<DomainBill> pageBillOfMonth(String wenyBankID, int order, int year, int month, int limit, long offset);

    String totalBillOfMonth(String wenyBankID, int year, int month);

}
