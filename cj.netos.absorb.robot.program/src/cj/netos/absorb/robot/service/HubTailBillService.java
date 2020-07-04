package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IHubTailBillService;
import cj.netos.absorb.robot.mapper.HubTailsMapper;
import cj.netos.absorb.robot.mapper.TailBillMapper;
import cj.netos.absorb.robot.model.HubTails;
import cj.netos.absorb.robot.model.HubTailsExample;
import cj.netos.absorb.robot.model.TailBill;
import cj.netos.absorb.robot.model.TailBillExample;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.Arrays;
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
    public long totalInBillOfMonth(String wenyBankID, int year, int month) {
        TailBillExample example = new TailBillExample();
        example.createCriteria().andBankidEqualTo(wenyBankID).andYearEqualTo(year).andMonthEqualTo(month - 1).andOrderIn(Arrays.asList(0,1));
        return tailBillMapper.countByExample(example);
    }

    @CjTransaction
    @Override
    public long totalInBillOfYear(String wenyBankID, int year) {
        TailBillExample example = new TailBillExample();
        example.createCriteria().andBankidEqualTo(wenyBankID).andYearEqualTo(year).andOrderIn(Arrays.asList(0,1));
        return tailBillMapper.countByExample(example);
    }

    @CjTransaction
    @Override
    public long totalOutBillOfMonth(String wenyBankID, int year, int month) {
        TailBillExample example = new TailBillExample();
        example.createCriteria().andBankidEqualTo(wenyBankID).andYearEqualTo(year).andMonthEqualTo(month - 1).andOrderEqualTo(2);
        return tailBillMapper.countByExample(example);
    }

    @CjTransaction
    @Override
    public long totalOutBillOfYear(String wenyBankID, int year) {
        TailBillExample example = new TailBillExample();
        example.createCriteria().andBankidEqualTo(wenyBankID).andYearEqualTo(year).andOrderEqualTo(2);
        return tailBillMapper.countByExample(example);
    }
}
