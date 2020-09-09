package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.IAbsorberBillService;
import cj.netos.absorb.robot.model.AbsorberBill;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/absorber/bill.ports")
public class AbsorberBillPorts implements IAbsorberBillPorts {
    @CjServiceRef
    IAbsorberBillService absorberBillService;

    @Override
    public List<AbsorberBill> pageBill(ISecuritySession securitySession, String absorber, int limit, long offset) throws CircuitException {
        return absorberBillService.pageBill(absorber, limit, offset);
    }

    @Override
    public List<AbsorberBill> getBillOfMonth(ISecuritySession securitySession, String absorber, int year, int month, int limit, long offset) throws CircuitException {
        return absorberBillService.getBillOfMonth(absorber, year, month, limit, offset);
    }

    @Override
    public List<AbsorberBill> pageBillOfMonth(ISecuritySession securitySession, String absorber, int order, int year, int month, int limit, long offset) throws CircuitException {
        return absorberBillService.pageBillOfMonth(absorber, order, year, month, limit, offset);
    }

    @Override
    public String totalBillOfMonth(ISecuritySession securitySession, String absorber, int year, int month) throws CircuitException {
        return absorberBillService.totalBillOfMonth(absorber, year, month);
    }
}
