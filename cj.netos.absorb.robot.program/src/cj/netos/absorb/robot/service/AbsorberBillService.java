package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IAbsorberBillService;
import cj.netos.absorb.robot.mapper.AbsorberBillMapper;
import cj.netos.absorb.robot.mapper.DomainBillMapper;
import cj.netos.absorb.robot.model.AbsorberBill;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "absorberBillService")
public class AbsorberBillService implements IAbsorberBillService {
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.AbsorberBillMapper")
    AbsorberBillMapper absorberBillMapper;

    @CjTransaction
    @Override
    public List<AbsorberBill> pageBill(String absorber, int limit, long offset) {
        return absorberBillMapper.page(absorber, limit, offset);
    }

    @CjTransaction
    @Override
    public List<AbsorberBill> getBillOfMonth(String absorber, int year, int month, int limit, long offset) {
        return absorberBillMapper.getBillOfMonth(absorber, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public List<AbsorberBill> pageBillOfMonth(String absorber, int order, int year, int month, int limit, long offset) {
        return absorberBillMapper.pageBillOfMonth(absorber, order, year, month, limit, offset);
    }

    @CjTransaction
    @Override
    public String totalBillOfMonth(String absorber, int year, int month) {
        return absorberBillMapper.totalBillOfMonth(absorber, year, month);
    }
}
