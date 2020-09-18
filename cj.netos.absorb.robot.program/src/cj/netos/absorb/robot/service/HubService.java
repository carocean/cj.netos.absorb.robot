package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.*;
import cj.netos.absorb.robot.bo.DomainBulletin;
import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.bo.RecipientsAbsorbBill;
import cj.netos.absorb.robot.bo.RecipientsSummary;
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
import cj.studio.openport.util.Encript;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.gson2.com.google.gson.reflect.TypeToken;
import cj.ultimate.util.StringUtil;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@CjBridge(aspects = "@transaction")
@CjService(name = "hubService")
public class HubService implements IHubService {
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
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.AbsorberBucketMapper")
    AbsorberBucketMapper absorberBucketMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.AbsorberBillMapper")
    AbsorberBillMapper absorberBillMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.DomainBucketMapper")
    DomainBucketMapper domainBucketMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.DomainBillMapper")
    DomainBillMapper domainBillMapper;
    @CjServiceSite
    IServiceSite site;

    @CjTransaction
    @Override
    public void createAbsorber(Absorber absorber) {
        absorberMapper.insert(absorber);
        _createAbsorberBucket(absorber);
    }

    @CjTransaction
    @Override
    public Absorber getAbsorber(String absorberid) {
        return absorberMapper.selectByPrimaryKey(absorberid);
    }

    @CjTransaction
    @Override
    public AbsorberBucket getAndInitAbsorbBucket(String absorberid) {
        AbsorberBucket bucket = absorberBucketMapper.selectByPrimaryKey(absorberid);
        if (bucket != null) {
            return bucket;
        }
        Absorber absorber = absorberMapper.selectByPrimaryKey(absorberid);
        if (absorber == null) {
            return null;
        }
        return _createAbsorberBucket(absorber);
    }

    private AbsorberBucket _createAbsorberBucket(Absorber absorber) {
        AbsorberBucket bucket = new AbsorberBucket();
        bucket.setAbsorber(absorber.getId());
        bucket.setBank(absorber.getBankid());
        bucket.setpInvestAmount(new BigDecimal("0.00"));
        bucket.setwInvestAmount(new BigDecimal("0.00"));
        bucket.setPrice(new BigDecimal("0.00"));
        bucket.setTimes(0L);
        bucket.setUtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorberBucketMapper.insert(bucket);
        return bucket;
    }

    @CjTransaction
    @Override
    public List<Absorber> pageAbsorber(String bankid, int type, int limit, long offset) {
        if (type == -1) {
            return absorberMapper.pageAny(bankid, limit, offset);
        }
        return absorberMapper.pageWhere(bankid, type, limit, offset);
    }

    @CjTransaction
    @Override
    public List<AbsorberBucket> pageAbsorberBucketInBullectin(DomainBulletin bulletin, int limit, int offset) {
        List<AbsorberBucket> buckets = absorberBucketMapper.pageAbsorberBucketGTEPrice(bulletin.getBucket().getBank(), bulletin.getBucket().getWaaPrice(), limit, offset);
        return buckets;
    }

    @CjTransaction
    @Override
    public List<AbsorberBucket> pageAbsorberBucket(String bankid, int limit, int offset) {
        return absorberBucketMapper.pageAbsorberBucket(bankid, limit, offset);
    }

    @CjTransaction
    //更新洇金桶的纹银银行投资余额并计算价格
    @Override
    public void updateAbsorbBucket0(AbsorberBucket bucket, BigDecimal realDistribute, BankWithdrawResult result) {
        BigDecimal winvestAmount = bucket.getwInvestAmount() == null ? BigDecimal.ZERO : bucket.getwInvestAmount();
        winvestAmount = winvestAmount.add(realDistribute);
        long times = bucket.getTimes() == null ? 0L : bucket.getTimes();
        times = times + 1;
        if (BigDecimal.ZERO.compareTo(winvestAmount) == 0) {
            winvestAmount = new BigDecimal("0.001");
        }
        //为公众投资总额除以纹银银行投资总额
        BigDecimal newPrice = bucket.getpInvestAmount().divide(winvestAmount, RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);

        AbsorberBill absorberBill = new AbsorberBill();
        absorberBill.setAbsorber(bucket.getAbsorber());
        absorberBill.setAmount(realDistribute);
        absorberBill.setpBalance(bucket.getpInvestAmount());
        absorberBill.setwBalance(winvestAmount);
        absorberBill.setAfterPrice(newPrice);
        absorberBill.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorberBill.setOrder(0);
        absorberBill.setSn(new IdWorker().nextId());
        absorberBill.setPerson(result.getWithdrawer());
        absorberBill.setRefsn(result.getSn());
        absorberBill.setNote("公众投资");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        absorberBill.setYear(calendar.get(Calendar.YEAR));
        absorberBill.setMonth(calendar.get(Calendar.MONTH));
        absorberBill.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        int season = calendar.get(Calendar.MONTH) % 4;
        absorberBill.setSeason(season);

        absorberBillMapper.insert(absorberBill);
        absorberBucketMapper.updateByWithdrawInvest(bucket.getAbsorber(), winvestAmount, times, newPrice, absorberBill.getCtime());
        //更新域桶,假设在此期间洇取器数量没变，只是增加了投资。（这种分发洇金的程序不需要指数准确）
        DomainBulletin bulletin = getDomainBulletin(bucket.getBank());
        BigDecimal weights = bulletin.getAbsorbWeights();
        long absorbCount = bulletin.getAbsorbCount();
        BigDecimal waaPrice = BigDecimal.ZERO;
        if (absorbCount > 0) {
            waaPrice = weights.divide(new BigDecimal(absorbCount + ""), RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);
        }

        DomainBill domainBill = new DomainBill();
        domainBill.setAbsorbCount(absorbCount);
        domainBill.setAbsorber(bucket.getAbsorber());
        domainBill.setAfterWaaPrice(waaPrice);
        domainBill.setCtime(absorberBill.getCtime());
        domainBill.setOrder(0);
        domainBill.setpInvestAmount(bucket.getpInvestAmount());
        domainBill.setwInvestAmount(winvestAmount);
        domainBill.setNote("纹银银行投资");
        domainBill.setWaaPrice(waaPrice);
        domainBill.setSn(new IdWorker().nextId());
        domainBill.setBankid(bucket.getBank());
        domainBill.setRefsn(absorberBill.getRefsn());

        domainBill.setDay(absorberBill.getDay());
        domainBill.setMonth(absorberBill.getMonth());
        domainBill.setYear(absorberBill.getYear());
        domainBill.setSeason(absorberBill.getSeason());

        domainBillMapper.insert(domainBill);
        domainBucketMapper.updateWaaPrice(bucket.getBank(), waaPrice, absorberBill.getCtime());
    }

    @CjTransaction
    //更新洇金桶的公众投资余额并计算价格
    @Override
    public void updateByPersonInvest(AbsorberBucket bucket, BigDecimal realDistribute, InvestRecord record) {
        BigDecimal pinvestAmount = bucket.getpInvestAmount() == null ? BigDecimal.ZERO : bucket.getpInvestAmount();
        pinvestAmount = pinvestAmount.add(realDistribute);
        long times = bucket.getTimes() == null ? 0L : bucket.getTimes();
        times = times + 1;
        if (BigDecimal.ZERO.compareTo(bucket.getwInvestAmount()) == 0) {
            bucket.setwInvestAmount(new BigDecimal("0.001"));
        }
        //为公众投资总额除以纹银银行投资总额
        BigDecimal newPrice = pinvestAmount.divide(bucket.getwInvestAmount(), RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);

        AbsorberBill absorberBill = new AbsorberBill();
        absorberBill.setAbsorber(bucket.getAbsorber());
        absorberBill.setAmount(realDistribute);
        absorberBill.setpBalance(pinvestAmount);
        absorberBill.setwBalance(bucket.getwInvestAmount());
        absorberBill.setAfterPrice(newPrice);
        absorberBill.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorberBill.setOrder(1);
        absorberBill.setSn(new IdWorker().nextId());
        absorberBill.setPerson(record.getInvester());
        absorberBill.setRefsn(record.getSn());
        absorberBill.setNote("公众投资");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        absorberBill.setYear(calendar.get(Calendar.YEAR));
        absorberBill.setMonth(calendar.get(Calendar.MONTH));
        absorberBill.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        int season = calendar.get(Calendar.MONTH) % 4;
        absorberBill.setSeason(season);

        absorberBillMapper.insert(absorberBill);
        absorberBucketMapper.updateByPersonInvest(bucket.getAbsorber(), pinvestAmount, times, newPrice, absorberBill.getCtime());
        //更新域桶,假设在此期间洇取器数量没变，只是增加了投资。（这种分发洇金的程序不需要指数准确）
        DomainBulletin bulletin = getDomainBulletin(bucket.getBank());
        BigDecimal weights = bulletin.getAbsorbWeights();
        long absorbCount = bulletin.getAbsorbCount();
        BigDecimal waaPrice = BigDecimal.ZERO;
        if (absorbCount > 0) {
            waaPrice = weights.divide(new BigDecimal(absorbCount + ""), RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN);
        }
        DomainBill domainBill = new DomainBill();
        domainBill.setAbsorbCount(absorbCount);
        domainBill.setAbsorber(bucket.getAbsorber());
        domainBill.setAfterWaaPrice(waaPrice);
        domainBill.setCtime(absorberBill.getCtime());
        domainBill.setOrder(1);
        domainBill.setpInvestAmount(pinvestAmount);
        domainBill.setwInvestAmount(bucket.getwInvestAmount());
        domainBill.setNote("公众投资");
        domainBill.setWaaPrice(waaPrice);
        domainBill.setSn(new IdWorker().nextId());
        domainBill.setBankid(bucket.getBank());
        domainBill.setRefsn(absorberBill.getRefsn());

        domainBill.setDay(absorberBill.getDay());
        domainBill.setMonth(absorberBill.getMonth());
        domainBill.setYear(absorberBill.getYear());
        domainBill.setSeason(absorberBill.getSeason());

        domainBillMapper.insert(domainBill);
        domainBucketMapper.updateWaaPrice(bucket.getBank(), waaPrice, absorberBill.getCtime());
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
        long maxRecipients = absorber.getMaxRecipients() == null ? Long.valueOf(site.getProperty("hub.absorber.maxRecipients")) : absorber.getMaxRecipients();
        if (maxRecipients > 0 && count > maxRecipients) {
            throw new CircuitException("500", String.format("已超出洇取器上限。洇取器:%s 人数:%s", absorber.getTitle(), count));
        }
        recipientsMapper.insert(recipients);
    }

    @CjTransaction
    @Override
    public void removeRecipients(String absorberid, String person) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(person);
        recipientsMapper.deleteByExample(example);
    }

    @CjTransaction
    @Override
    public boolean existsRecipients2(String absorberid, String person, String encourageCode) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(person).andEncourageCodeEqualTo(encourageCode);
        return recipientsMapper.countByExample(example) > 0;
    }

    @CjTransaction
    @Override
    public void removeRecipients2(String absorberid, String person, String encourageCode) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(person).andEncourageCodeEqualTo(encourageCode);
        recipientsMapper.deleteByExample(example);
    }

    @CjTransaction
    @Override
    public void updateMaxRecipients(String absorberid, long maxRecipients) {
        absorberMapper.updateMaxRecipients(absorberid, maxRecipients);
    }

    @CjTransaction
    @Override
    public void updateRecipientsWeights(String absorberid, String person, String encourageCode, BigDecimal weights) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(person).andEncourageCodeEqualTo(encourageCode);
        List<Recipients> recipientsList = recipientsMapper.selectByExample(example);
        if (recipientsList.isEmpty()) {
            return;
        }
        Recipients recipients = recipientsList.get(0);
        BigDecimal weight = recipients.getWeight();
        if (weight != null) {
            weights = weights.add(weight);
        }
        recipientsMapper.updateWeights(absorberid, person, encourageCode, weights);
    }

    @CjTransaction
    @Override
    public Recipients getRecipients(String recipientsId) {
        return recipientsMapper.selectByPrimaryKey(recipientsId);
    }

    @CjTransaction
    @Override
    public void updateRecipientsWeights(String recipientsId, BigDecimal weights) {
        if (weights == null || weights.compareTo(BigDecimal.ZERO) < 0) {
            weights = BigDecimal.ZERO;
        }
        recipientsMapper.updateWeight(recipientsId, weights);
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
    public boolean existsRecipientsEncourageCode(String principal, String absorberid, String encourageCode) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(principal).andEncourageCodeEqualTo(encourageCode);
        return recipientsMapper.countByExample(example) > 0;
    }

    @CjTransaction
    @Override
    public void addWeightToRecipients(String principal, String absorberid) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(principal);
        List<Recipients> list = recipientsMapper.selectByExample(example);
        if (list.isEmpty()) {
            return;
        }
        Recipients recipients = list.get(0);
        BigDecimal weight = recipients.getWeight();
        weight = weight.add(new BigDecimal("0.15"));
        recipientsMapper.updateWeight(recipients.getId(), weight);
    }

    @CjTransaction
    @Override
    public void addCommentWeightsOfRecipients(String absorberid, String principal, String encourageCode) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(principal).andEncourageCodeEqualTo(encourageCode);
        List<Recipients> list = recipientsMapper.selectByExample(example);
        if (list.isEmpty()) {
            return;
        }
        Recipients recipients = list.get(0);
        BigDecimal weight = recipients.getWeight();
        weight = weight.add(new BigDecimal("0.15"));
        recipientsMapper.updateWeight(recipients.getId(), weight);
    }

    @CjTransaction
    @Override
    public boolean subCommentWeightOfRecipients(String absorberid, String principal, String encourageCode) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(principal).andEncourageCodeEqualTo(encourageCode);
        List<Recipients> list = recipientsMapper.selectByExample(example);
        if (list.isEmpty()) {
            return false;
        }
        Recipients recipients = list.get(0);
        BigDecimal weight = recipients.getWeight();
        if (new BigDecimal("1.5").compareTo(weight) == 0) {
            return false;
        }
        weight = weight.subtract(new BigDecimal("0.15"));
        recipientsMapper.updateWeight(recipients.getId(), weight);
        return true;
    }

    @CjTransaction
    @Override
    public DomainBulletin getDomainBulletin(String bankid) {
        DomainBucket domainBucket = getAndInitDomainBucket(bankid);
        TotalAbsorber totalAbsorber = absorberBucketMapper.totalAbsorber(bankid);
        if (totalAbsorber == null) {
            totalAbsorber = new TotalAbsorber();
            AbsorberBucketExample example = new AbsorberBucketExample();
            example.createCriteria().andBankEqualTo(bankid).andPriceGreaterThanOrEqualTo(domainBucket.getWaaPrice());
            long count = absorberBucketMapper.countByExample(example);
            BigDecimal weights = new BigDecimal((count / 0.001) + "");
            totalAbsorber.setPrice(weights);
            totalAbsorber.setCount(count);
        }
        DomainBulletin bulletin = new DomainBulletin();
        bulletin.setBucket(domainBucket);
        bulletin.setAbsorbWeights(totalAbsorber.getPrice());
        bulletin.setAbsorbCount(totalAbsorber.getCount());
        return bulletin;
    }

    @CjTransaction
    @Override
    public DomainBulletin getDomainBulletinBeginWaaPrice(String bankid) {
        DomainBucket domainBucket = getAndInitDomainBucket(bankid);
        TotalAbsorber totalAbsorber = absorberBucketMapper.totalAbsorberBeginWaaPrice(bankid, domainBucket.getWaaPrice());
        if (totalAbsorber == null) {
            totalAbsorber = new TotalAbsorber();
            AbsorberBucketExample example = new AbsorberBucketExample();
            example.createCriteria().andBankEqualTo(bankid).andPriceGreaterThanOrEqualTo(domainBucket.getWaaPrice());
            long count = absorberBucketMapper.countByExample(example);
            BigDecimal weights = new BigDecimal((count / 0.001) + "");
            totalAbsorber.setPrice(weights);
            totalAbsorber.setCount(count);
        }
        DomainBulletin bulletin = new DomainBulletin();
        bulletin.setBucket(domainBucket);
        bulletin.setAbsorbWeights(totalAbsorber.getPrice());
        bulletin.setAbsorbCount(totalAbsorber.getCount());
        return bulletin;
    }

    @CjTransaction
    private DomainBucket getAndInitDomainBucket(String bankid) {
        DomainBucket bucket = domainBucketMapper.selectByPrimaryKey(bankid);
        if (bucket != null) {
            return bucket;
        }
        bucket = new DomainBucket();
        bucket.setBank(bankid);
        bucket.setUtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        bucket.setWaaPrice(BigDecimal.ZERO);
        domainBucketMapper.insert(bucket);
        return bucket;
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
    public List<Recipients> pageSimpleRecipients(String absorber, int limit, long offset) {
        return recipientsMapper.page(absorber, limit, offset);
    }

    @CjTransaction
    @Override
    public List<Recipients> pageRecipientsByPerson(String absorberid, String principal, int limit, long offset) {
        return recipientsMapper.page2(absorberid, principal, limit, offset);
    }

    @CjTransaction
    @Override
    public List<Recipients> pageGeoRecipients(Absorber absorber, int limit, long offset) throws CircuitException {
        List<POR> porList = searchAroundPerson(absorber.getLocation(), absorber.getRadius(), limit, offset);
        if (porList.isEmpty()) {
            return new ArrayList<>();
        }
        //求权和，把半径-离中心的距离加起来作为权和
        List<Recipients> recipientsList = new ArrayList<>();
        BigDecimal totalWeightsOfRecipients = new BigDecimal("0.00");
        for (POR por : porList) {
            BigDecimal weight = new BigDecimal(absorber.getRadius() + "").subtract(por.getDistance());//离中心越近权重越大，故而半径减之
            //越近权重越高,当离圈后就取消洇金了
            GeoReceptorBO bo = por.getReceptor();
            Recipients recipients = new Recipients();
            recipients.setId(bo.getId());
            recipients.setCtime(RobotUtils.dateTimeToMicroSecond(por.getReceptor().getCtime()));
            recipients.setPerson(bo.getCreator());
            recipients.setPersonName(bo.getTitle());
            recipients.setEncourageCode("enter");
            recipients.setEncourageCause("在圈内");
            recipients.setDesireAmount(0L);
            recipients.setAbsorber(absorber.getId());
            recipients.setWeight(weight);//距中心位置作为权重
            recipients.setDistance(por.getDistance());
            recipientsList.add(recipients);

            totalWeightsOfRecipients = totalWeightsOfRecipients.add(weight);
        }
        return recipientsList;
    }

    @CjTransaction
    @Override
    public List<RecipientsSummary> pageRecipientsSummary(String absorberid, int limit, long offset) {
        return recipientsMapper.pageSummary(absorberid, limit, offset);
    }

    @CjTransaction
    @Override
    public HubTails getAndInitHubTails(String bankid) {
        HubTailsExample example = new HubTailsExample();
        example.createCriteria().andBankidEqualTo(bankid);
        List<HubTails> list = hubTailsMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        HubTails hubTails = new HubTails();
        hubTails.setId(new IdWorker().nextId());
        hubTails.setTailAdmount(new BigDecimal("0.00"));
        hubTails.setBankid(bankid);
        hubTailsMapper.insert(hubTails);
        return hubTails;
    }

    @CjTransaction
    @Override
    public long countRecipients(String absorberid) {
        return recipientsMapper.countRecipients(absorberid);
    }

    @CjTransaction
    @Override
    public TailBill withdrawHubTails(String principal, String bankid) {
        HubTails hubTails = getAndInitHubTails(bankid);
        BigDecimal tails = hubTails.getTailAdmount();
        BigDecimal amount = tails.setScale(0, RoundingMode.DOWN);//舍掉尾数即可提金额
        if (amount.compareTo(new BigDecimal("0.0")) <= 0) {
            return null;
        }
        TailBill bill = new TailBill();
        bill.setPerson(principal);
        bill.setAmount(amount.multiply(new BigDecimal("-1.00")).setScale(RobotUtils.BIGDECIMAL_SCALE, RoundingMode.DOWN));
        bill.setBalance(tails.add(bill.getAmount()));
        bill.setOrder(2);
        bill.setRefsn(null);
        bill.setBankid(bankid);
        bill.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        bill.setSn(new IdWorker().nextId());
        bill.setNote("洇取器尾金存入");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        bill.setYear(calendar.get(Calendar.YEAR));
        bill.setMonth(calendar.get(Calendar.MONTH));
        bill.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        int season = calendar.get(Calendar.MONTH) % 4;
        bill.setSeason(season);

        tailBillMapper.insert(bill);

        hubTailsMapper.updateAmount(hubTails.getId(), bill.getBalance());
        return bill;
    }

    @CjTransaction
    @Override
    public void addTailAmount(BigDecimal amount, String person, String bankid, String refsn, int order, String cause) {
        HubTails hubTails = getAndInitHubTails(bankid);

        TailBill bill = new TailBill();
        bill.setPerson(person);
        bill.setAmount(amount);
        bill.setBalance(hubTails.getTailAdmount().add(amount));
        bill.setOrder(order);
        bill.setRefsn(refsn);
        bill.setBankid(bankid);
        bill.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        bill.setSn(new IdWorker().nextId());
        bill.setNote(cause);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        bill.setYear(calendar.get(Calendar.YEAR));
        bill.setMonth(calendar.get(Calendar.MONTH));
        bill.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        int season = calendar.get(Calendar.MONTH) % 4;
        bill.setSeason(season);

        tailBillMapper.insert(bill);

        hubTailsMapper.updateAmount(hubTails.getId(), bill.getBalance());
    }

    @CjTransaction
    @Override
    public RecipientsAbsorbBill addRecipientsRecord(Absorber absorber, Recipients recipients, Object result, BigDecimal money) {
        RecipientsRecord record = new RecipientsRecord();
        record.setAmount(money);
        record.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setRecipient(recipients.getPerson());
        record.setAbsorber(absorber.getId());
        record.setEncourageCause(recipients.getEncourageCause());
        record.setEncourageCode(recipients.getEncourageCode());
        record.setRecipientsId(recipients.getId());
        if (result instanceof BankWithdrawResult) {
            BankWithdrawResult bankWithdrawResult = (BankWithdrawResult) result;
            record.setRefsn(bankWithdrawResult.getOutTradeSn());
            record.setOrder(0);
        }
        if (result instanceof InvestRecord) {
            InvestRecord investRecord = (InvestRecord) result;
            record.setRefsn(investRecord.getSn());
            record.setOrder(1);
        }
        record.setSn(new IdWorker().nextId());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        record.setYear(calendar.get(Calendar.YEAR));
        record.setMonth(calendar.get(Calendar.MONTH));

        recipientsRecordMapper.insert(record);

        return new RecipientsAbsorbBill(absorber.getTitle(), recipients, money, record.getSn());
    }

    @CjTransaction
    @Override
    public long getMaxRecipientsCount(Absorber absorber) {
        return absorber.getMaxRecipients() == null ? Long.valueOf(site.getProperty("hub.absorber.maxRecipients")) : absorber.getMaxRecipients();
    }

    @CjTransaction
    @Override
    public List<POR> searchAroundPerson(String location, Long radius, int limit, long offset) throws CircuitException {
        OkHttpClient client = (OkHttpClient) site.getService("@.http");

        String appid = site.getProperty("appid");
        String appKey = site.getProperty("appKey");
        String appSecret = site.getProperty("appSecret");
        String linkPorts = site.getProperty("rhub.ports.link.geo");

        String nonce = Encript.md5(String.format("%s%s", UUID.randomUUID().toString(), System.currentTimeMillis()));
        String sign = Encript.md5(String.format("%s%s%s", appKey, nonce, appSecret));
        String url = String.format("%s?location=%s&radius=%s&geoType=mobiles&limit=%s&offset=%s", linkPorts, location, radius, limit, offset);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Rest-Command", "searchAroundLocation")
                .addHeader("app-id", appid)
                .addHeader("app-key", appKey)
                .addHeader("app-nonce", nonce)
                .addHeader("app-sign", sign)
                .get()
                .build();
        final Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        if (response.code() >= 400) {
            throw new CircuitException("1002", String.format("远程访问失败:%s", response.message()));
        }
        String json = null;
        try {
            json = response.body().string();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        Map<String, Object> map = new Gson().fromJson(json, HashMap.class);
        if (Double.parseDouble(map.get("status") + "") >= 400) {
            throw new CircuitException(map.get("status") + "", map.get("message") + "");
        }
        json = (String) map.get("dataText");
        return new Gson().fromJson(json, new TypeToken<ArrayList<POR>>() {
        }.getType());
    }

    @CjTransaction
    @Override
    public void stopAbsorber(String absorber, String exitCause) {
        absorberMapper.stop(absorber, exitCause);
    }

    @CjTransaction
    @Override
    public void startAbsorber(String absorberid) {
        absorberMapper.start(absorberid);
    }

    @CjTransaction
    @Override
    public boolean isBindingsAbsorbabler(String absorberid, String absorbabler) {
        Absorber absorber = getAbsorber(absorberid);
        if (absorber == null) {
            return false;
        }
        if (StringUtil.isEmpty(absorber.getAbsorbabler())) {
            return false;
        }
        return absorber.getAbsorbabler().equals(absorbabler);
    }

    @CjTransaction
    @Override
    public void updateAbsorberLocation(String absorberid, LatLng location) {
        absorberMapper.updateLocation(absorberid, new Gson().toJson(location));
    }

    @CjTransaction
    @Override
    public void updateAbsorberRadius(String absorberid, long radius) {
        absorberMapper.updateRadius(absorberid, radius);
    }

    @CjTransaction
    @Override
    public void bindAbsorbabler(String absorberid, String absorbabler) throws CircuitException {
        AbsorberExample example = new AbsorberExample();
        example.createCriteria().andAbsorbablerEqualTo(absorbabler);
        if (absorberMapper.countByExample(example) > 0) {
            throw new CircuitException("500", String.format("该可洇取物:%s 已被其它洇取器绑定", absorbabler));
        }
        absorberMapper.updateAbsorbabler(absorberid, absorbabler);
    }

    @CjTransaction
    @Override
    public void unbindAbsorbabler(String absorberid) {
        absorberMapper.updateAbsorbabler(absorberid, null);
    }

    @CjTransaction
    @Override
    public Absorber getAbsorberByAbsorbabler(String absorbabler) {
        AbsorberExample example = new AbsorberExample();
        example.createCriteria().andAbsorbablerEqualTo(absorbabler);
        List<Absorber> list = absorberMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
