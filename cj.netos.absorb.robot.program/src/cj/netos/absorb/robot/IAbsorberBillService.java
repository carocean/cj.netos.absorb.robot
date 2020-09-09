package cj.netos.absorb.robot;

import cj.netos.absorb.robot.model.AbsorberBill;

import java.util.List;

public interface IAbsorberBillService {
    List<AbsorberBill> pageBill(String absorber, int limit, long offset);

    List<AbsorberBill> getBillOfMonth(String absorber, int year, int month, int limit, long offset);

    List<AbsorberBill> pageBillOfMonth(String absorber, int order, int year, int month, int limit, long offset);

    String totalBillOfMonth(String absorber, int year, int month);

}
