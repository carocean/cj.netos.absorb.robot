package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IHubTailBillService;
import cj.netos.absorb.robot.mapper.TailBillMapper;
import cj.netos.absorb.robot.model.HubTails;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;
import java.util.List;


@CjBridge(aspects = "@transaction")
@CjService(name = "hubTailBillService")
public class HubTailBillService implements IHubTailBillService {
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.TailBillMapper")
    TailBillMapper tailBillMapper;

    @CjTransaction
    @Override
    public List<HubTails> pageBill(String wenyBankID, int limit, long offset) {
        return tailBillMapper.page(wenyBankID, limit, offset);
    }

    @CjTransaction
    @Override
    public List<HubTails> getBillOfMonth(String wenyBankID, int year, int month, int limit, long offset) {
        return tailBillMapper.getBillOfMonth(wenyBankID, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public List<HubTails> pageBillOfMonth(String wenyBankID, int order, int year, int month, int limit, long offset) {
        return tailBillMapper.pageBillOfMonth(wenyBankID, order, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public BigDecimal totalInBillOfMonth(String wenyBankID, int year, int month) {
        return tailBillMapper.totalInBillOfMonth(wenyBankID,year,month);
    }

    @CjTransaction
    @Override
    public BigDecimal totalInBillOfYear(String wenyBankID, int year) {
        return tailBillMapper.totalInBillOfYear(wenyBankID,year);
    }

    @CjTransaction
    @Override
    public BigDecimal totalOutBillOfMonth(String wenyBankID, int year, int month) {
        return tailBillMapper.totalOutBillOfMonth(wenyBankID,year,month);
    }

    @CjTransaction
    @Override
    public BigDecimal totalOutBillOfYear(String wenyBankID, int year) {
        return tailBillMapper.totalOutBillOfYear(wenyBankID,year);
    }
}
