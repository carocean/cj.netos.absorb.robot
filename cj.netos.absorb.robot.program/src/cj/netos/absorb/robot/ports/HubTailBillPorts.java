package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.IHubTailBillService;
import cj.netos.absorb.robot.model.HubTails;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/bill/hubTails.ports")
public class HubTailBillPorts implements IHubTailBillPorts {
    @CjServiceRef
    IHubTailBillService hubTailBillService;

    @Override
    public List<HubTails> pageBill(ISecuritySession securitySession, String wenyBankID, int limit, long offset) throws CircuitException {
        return hubTailBillService.pageBill(wenyBankID, limit, offset);
    }

    @Override
    public List<HubTails> getBillOfMonth(ISecuritySession securitySession, String wenyBankID, int year, int month, int limit, long offset) throws CircuitException {
        return hubTailBillService.getBillOfMonth(wenyBankID, year, month, limit, offset);
    }

    @Override
    public List<HubTails> pageBillOfMonth(ISecuritySession securitySession, String wenyBankID, int order, int year, int month, int limit, long offset) throws CircuitException {
        return hubTailBillService.pageBillOfMonth(wenyBankID,order, year, month, limit, offset);
    }

    @Override
    public long totalInBillOfMonth(ISecuritySession securitySession, String wenyBankID, int year, int month) throws CircuitException {
        return hubTailBillService.totalInBillOfMonth(wenyBankID,year,month);
    }

    @Override
    public long totalInBillOfYear(ISecuritySession securitySession, String wenyBankID, int year) throws CircuitException {
        return hubTailBillService.totalInBillOfYear(wenyBankID,year);
    }

    @Override
    public long totalOutBillOfMonth(ISecuritySession securitySession, String wenyBankID, int year, int month) throws CircuitException {
        return hubTailBillService.totalOutBillOfMonth(wenyBankID, year, month);
    }

    @Override
    public long totalOutBillOfYear(ISecuritySession securitySession, String wenyBankID, int year) throws CircuitException {
        return hubTailBillService.totalOutBillOfYear(wenyBankID,year);
    }
}
