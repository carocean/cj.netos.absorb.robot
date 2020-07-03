package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IHubTailBillService;
import cj.netos.absorb.robot.model.HubTails;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;


@CjBridge(aspects = "@transaction")
@CjService(name = "hubTailBillService")
public class HubTailBillService implements IHubTailBillService {
    @CjTransaction
    @Override
    public List<HubTails> pageBill(String wenyBankID, int limit, long offset) {
        return null;
    }

    @CjTransaction
    @Override
    public List<HubTails> getBillOfMonth(String wenyBankID, int year, int month, int limit, long offset) {
        return null;
    }

    @CjTransaction
    @Override
    public List<HubTails> pageBillOfMonth(String wenyBankID, int order, int year, int month, int limit, long offset) {
        return null;
    }

    @CjTransaction
    @Override
    public long totalInBillOfMonth(String wenyBankID, int year, int month) {
        return 0;
    }

    @CjTransaction
    @Override
    public long totalInBillOfYear(String wenyBankID, int year) {
        return 0;
    }

    @CjTransaction
    @Override
    public long totalOutBillOfMonth(String wenyBankID, int year, int month) {
        return 0;
    }

    @CjTransaction
    @Override
    public long totalOutBillOfYear(String wenyBankID, int year) {
        return 0;
    }
}
