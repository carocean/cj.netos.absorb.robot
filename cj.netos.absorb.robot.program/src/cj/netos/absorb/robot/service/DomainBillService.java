package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IDomainBillService;
import cj.netos.absorb.robot.mapper.DomainBillMapper;
import cj.netos.absorb.robot.model.DomainBill;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "domainBillService")
public class DomainBillService implements IDomainBillService {
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.DomainBillMapper")
    DomainBillMapper domainBillMapper;

    @CjTransaction
    @Override
    public List<DomainBill> pageBill(String wenyBankID, int limit, long offset) {
        return domainBillMapper.page(wenyBankID, limit, offset);
    }

    @CjTransaction
    @Override
    public List<DomainBill> getBillOfMonth(String wenyBankID, int year, int month, int limit, long offset) {
        return domainBillMapper.getBillOfMonth(wenyBankID, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public List<DomainBill> pageBillOfMonth(String wenyBankID, int order, int year, int month, int limit, long offset) {
        return domainBillMapper.pageBillOfMonth(wenyBankID, order, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public String totalBillOfMonth(String wenyBankID, int year, int month) {
        return domainBillMapper.totalBillOfMonth(wenyBankID, year, month);
    }
}
