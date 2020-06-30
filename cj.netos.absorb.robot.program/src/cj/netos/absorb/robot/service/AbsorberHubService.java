package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.BankWithdrawResult;
import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.bo.RecipientsAbsorbBill;
import cj.netos.absorb.robot.mapper.*;
import cj.netos.absorb.robot.model.*;
import cj.netos.absorb.robot.util.IdWorker;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CjBridge(aspects = "@transaction")
@CjService(name = "absorberHubService")
public class AbsorberHubService implements IAbsorberHubService {

    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.AbsorberMapper")
    AbsorberMapper absorberMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.RecipientsMapper")
    RecipientsMapper recipientsMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.HubTailsMapper")
    HubTailsMapper hubTailsMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.TailBillMapper")
    TailBillMapper tailBillMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.RecipientsRecordMapper")
    RecipientsRecordMapper recipientsRecordMapper;
    @CjServiceSite
    IServiceSite site;

    @CjTransaction
    @Override
    public void createSimpleAbsorber(Absorber absorber) {
        absorberMapper.insert(absorber);
    }

    @CjTransaction
    @Override
    public Absorber getAbsorber(String absorberid) {
        return absorberMapper.selectByPrimaryKey(absorberid);
    }

    @CjTransaction
    @Override
    public List<Absorber> pageAbsorber(String bankid, int type, int limit, long offset) {
        switch (type) {
            case 0://任何洇取器
                return absorberMapper.pageAny(bankid, limit, offset);
            case 1://非地理洇取器
                return absorberMapper.pageWhere(bankid, 0, limit, offset);
            case 2://地理洇取器
                return absorberMapper.pageWhere(bankid, 1, limit, offset);
            default:
                return new ArrayList<>();
        }

    }

    @CjTransaction
    @Override
    public void removeAbsorber(String absorberid) {
        absorberMapper.deleteByPrimaryKey(absorberid);
    }

    @CjTransaction
    @Override
    public void addRecipients(Recipients recipients) throws CircuitException {
        Absorber absorber = getAbsorber(recipients.getAbsorber());
        long count = totalRecipientsCount(absorber.getId());
        long maxRecipients = absorber.getMaxRecipients() == null ? Long.valueOf(site.getProperty("hub.absorber.naxRecipients")) : absorber.getMaxRecipients();
        if (count > maxRecipients) {
            throw new CircuitException("500", String.format("已超出洇取器上限。洇取器:%s 人数:%s", absorber.getTitle(), count));
        }
        recipientsMapper.insert(recipients);
    }

    @CjTransaction
    @Override
    public long totalRecipientsCount(String absorber) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorber);
        return recipientsMapper.countByExample(example);
    }

    @CjTransaction
    @Override
    public boolean existsRecipients(String principal, String absorberid) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(principal);
        return recipientsMapper.countByExample(example) > 0;
    }

    @CjTransaction
    @Override
    public BigDecimal totalWeightsOfAbsorber(String bankid) {
        BigDecimal v = absorberMapper.totalWeightsOfAbsorber(bankid);
        if (v == null) {
            return new BigDecimal("0.00");
        }
        return v;
    }

    @CjTransaction
    @Override
    public BigDecimal totalWeightsOfRecipients(String absorberid) {
        BigDecimal v = recipientsMapper.totalWeightsOfRecipients(absorberid);
        if (v == null) {
            return new BigDecimal("0.00");
        }
        return v;
    }

    @CjTransaction
    @Override
    public List<Recipients> pageRecipients(String absorber, int limit, long offset) {
        return recipientsMapper.page(absorber, limit, offset);
    }

    @CjTransaction
    @Override
    public void addTailAmount(BigDecimal remainingAmount, BankWithdrawResult result, String cause) {
        HubTailsExample example = new HubTailsExample();
        example.createCriteria();
        List<HubTails> list = hubTailsMapper.selectByExample(example);
        HubTails hubTails = null;
        if (list.isEmpty()) {
            hubTails = new HubTails();
            hubTails.setId(UUID.randomUUID().toString());
            hubTails.setTailAdmount(remainingAmount);
            hubTailsMapper.insert(hubTails);
        } else {
            hubTails = list.get(0);
            hubTails.setTailAdmount(hubTails.getTailAdmount().add(remainingAmount));
            hubTailsMapper.updateByPrimaryKey(hubTails);
        }
        TailBill bill = new TailBill();
        bill.setAmount(remainingAmount);
        bill.setBalance(hubTails.getTailAdmount());
        bill.setOrder(0);
        bill.setRefsn(result.getOutTradeSn());
        bill.setBankid(result.getBankid());
        bill.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        bill.setSn(new IdWorker().nextId());
        bill.setNote(cause);
        tailBillMapper.insert(bill);
    }

    @CjTransaction
    @Override
    public RecipientsAbsorbBill addRecipientsRecord(Absorber absorber, Recipients recipients, Object result, BigDecimal money) {
        RecipientsRecord record = new RecipientsRecord();
        record.setAmount(money);
        record.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setRecipient(recipients.getPerson());
        record.setAbsorber(absorber.getId());
        if (result instanceof BankWithdrawResult) {
            BankWithdrawResult bankWithdrawResult = (BankWithdrawResult) result;
            record.setRefsn(bankWithdrawResult.getOutTradeSn());
        }
        if (result instanceof AbsorberBill) {
            AbsorberBill absorberBill = (AbsorberBill) result;
            record.setRefsn(absorberBill.getSn());
        }
        record.setSn(new IdWorker().nextId());
        recipientsRecordMapper.insert(record);

        return new RecipientsAbsorbBill(absorber, recipients, money, record.getSn());
    }
}
