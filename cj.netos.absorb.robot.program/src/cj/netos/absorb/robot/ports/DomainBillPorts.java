package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.IDomainBillService;
import cj.netos.absorb.robot.IHubTailBillService;
import cj.netos.absorb.robot.model.DomainBill;
import cj.netos.absorb.robot.model.HubTails;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/domain/bill.ports")
public class DomainBillPorts implements IDomainBillPorts{
    @CjServiceRef
    IDomainBillService domainBillService;
    @Override
    public List<DomainBill> pageBill(ISecuritySession securitySession, String wenyBankID, int limit, long offset) throws CircuitException {
        return domainBillService.pageBill(wenyBankID,limit,offset);
    }

    @Override
    public List<DomainBill> getBillOfMonth(ISecuritySession securitySession, String wenyBankID, int year, int month, int limit, long offset) throws CircuitException {
        return domainBillService.getBillOfMonth(wenyBankID,year,month,limit,offset);
    }

    @Override
    public List<DomainBill> pageBillOfMonth(ISecuritySession securitySession, String wenyBankID, int order, int year, int month, int limit, long offset) throws CircuitException {
        return domainBillService.pageBillOfMonth(wenyBankID,order,year,month,limit,offset);
    }

    @Override
    public String totalBillOfMonth(ISecuritySession securitySession, String wenyBankID, int year, int month) throws CircuitException {
        return domainBillService.totalBillOfMonth(wenyBankID,year,month);
    }
}
