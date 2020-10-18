package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.*;
import cj.netos.absorb.robot.bo.*;
import cj.netos.absorb.robot.mapper.*;
import cj.netos.absorb.robot.model.*;
import cj.netos.absorb.robot.result.QrcodeSliceResult;
import cj.netos.absorb.robot.result.QrcodeSliceTemplateResult;
import cj.netos.absorb.robot.result.TemplatePropResult;
import cj.netos.absorb.robot.util.IdWorker;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.CJSystem;
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
import com.rabbitmq.client.AMQP;
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
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.SliceTemplateMapper")
    SliceTemplateMapper sliceTemplateMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.TemplatePropMapper")
    TemplatePropMapper templatePropMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.QrcodeSliceMapper")
    QrcodeSliceMapper qrcodeSliceMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.SlicePropMapper")
    SlicePropMapper slicePropMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.SliceBatchMapper")
    SliceBatchMapper sliceBatchMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.RecipientsBalanceMapper")
    RecipientsBalanceMapper recipientsBalanceMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.RecipientsBalanceBillMapper")
    RecipientsBalanceBillMapper recipientsBalanceBillMapper;
    @CjServiceSite
    IServiceSite site;
    @CjServiceRef(refByName = "@.rabbitmq.producer.distributeAbsorbsToWallet")
    IRabbitMQProducer rabbitMQProducer;

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
    public List<Absorber> pageMyAbsorber(String creator, int type, int limit, long offset) {
        if (type == -1) {
            return absorberMapper.pageAnyByCreator(creator, limit, offset);
        }
        return absorberMapper.pageByCreator(creator, type, limit, offset);
    }

    @CjTransaction
    @Override
    public List<Absorber> pageMyAbsorberByUsage(String creator, int usage, int limit, long offset) {
        if (usage == -1) {
            return absorberMapper.pageAnyByCreator(creator, limit, offset);
        }
        return absorberMapper.pageByCreatorAndUsage(creator, usage, limit, offset);
    }

    @CjTransaction
    @Override
    public List<Absorber> pageJioninAbsorberByUsage(String creator, int usage, int limit, long offset) {
        if (usage == -1) {
            return absorberMapper.pageAnyJioninAbsorberByUsage(creator, limit, offset);
        }
        return absorberMapper.pageJioninAbsorberByUsage(creator, usage, limit, offset);
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
    public boolean existsPubingSliceRecipients(String principal, String absorberid) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria()
                .andAbsorberEqualTo(absorberid)
                .andPersonEqualTo(principal)
                .andEncourageCodeEqualTo("pubSlice");
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
    public List<Recipients> pageSimpleRecipientsByPerson(String absorberid, String principal, int limit, long offset) {
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
    public List<Recipients> getAroundLocationByPerson(Absorber absorber, String principal) throws CircuitException {
        List<POR> porList = searchPersonAround(absorber.getLocation(), absorber.getRadius(), principal);
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
        record.setEncourageBy(recipients.getEncourageBy());
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

    private List<POR> searchPersonAround(String location, Long radius, String principal) throws CircuitException {
        OkHttpClient client = (OkHttpClient) site.getService("@.http");

        String appid = site.getProperty("appid");
        String appKey = site.getProperty("appKey");
        String appSecret = site.getProperty("appSecret");
        String linkPorts = site.getProperty("rhub.ports.link.geo");

        String nonce = Encript.md5(String.format("%s%s", UUID.randomUUID().toString(), System.currentTimeMillis()));
        String sign = Encript.md5(String.format("%s%s%s", appKey, nonce, appSecret));
        String url = String.format("%s?location=%s&radius=%s&geoType=mobiles&person=%s", linkPorts, location, radius, principal);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Rest-Command", "searchPersonAroundLocation")
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

    @CjTransaction
    @Override
    public void configQrcodeSliceTemplate(List<QrcodeSliceTemplateBO> templates) {
        emptySliceTemplate();
        for (QrcodeSliceTemplateBO bo : templates) {
            SliceTemplate template = bo.createTemplate();
            sliceTemplateMapper.insert(template);
            List<TemplateProp> props = bo.createProps();
            for (TemplateProp prop : props) {
                templatePropMapper.insert(prop);
            }
        }
    }

    @CjTransaction
    @Override
    public void emptySliceTemplate() {
        SliceTemplateExample example = new SliceTemplateExample();
        example.createCriteria();
        sliceTemplateMapper.deleteByExample(example);
        TemplatePropExample example1 = new TemplatePropExample();
        example1.createCriteria();
        templatePropMapper.deleteByExample(example1);
    }

    @CjTransaction
    @Override
    public boolean cannotCreateQrocdeSlice(String principal) {
        //只要用户下存在state!=1的码片就不能创建新码片
        QrcodeSliceExample example = new QrcodeSliceExample();
        example.createCriteria().andCreatorEqualTo(principal).andStateNotEqualTo(1);
        return this.qrcodeSliceMapper.countByExample(example) > 0;
    }

    @CjTransaction
    @Override
    public List<QrcodeSliceResult> createQrcodeSlice(String principal, String nickName, QrcodeSliceTemplateResult sliceTemplate, long expire, LatLng location, long radius, String originAbsorber, String originPerson, int count, String note) throws CircuitException {
        SliceBatch batch = new SliceBatch();
        batch.setCreator(principal);
        batch.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        batch.setId(new IdWorker().nextId());
        sliceBatchMapper.insert(batch);

        String href = site.getProperty("qrcodeSlice.href");
        List<QrcodeSliceResult> qrcodeSliceResults = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            QrcodeSlice slice = new QrcodeSlice();
            slice.setBatchNo(batch.getId());
            slice.setCreator(principal);
            slice.setCtime(batch.getCtime());
            slice.setExpire(expire);
            slice.setId(new IdWorker().nextId());
            slice.setLocation(location.toJson());
            slice.setRadius(radius);
            slice.setCname(nickName);
            slice.setMaxAbsorbers(sliceTemplate.getMaxAbsorbers());
            slice.setState(-1);
            slice.setTemplate(sliceTemplate.getId());
            slice.setNote(note);
            slice.setOriginPerson(originPerson);
            slice.setOriginAbsorber(originAbsorber);
            slice.setHref(href);
            qrcodeSliceMapper.insert(slice);

            List<SliceProp> sliceProps = new ArrayList<>();
            List<TemplatePropResult> props = sliceTemplate.getProperties();
            for (TemplatePropResult r : props) {
                SliceProp prop = new SliceProp();
                prop.setPropId(r.getId());
                prop.setName(r.getName());
                prop.setValue(r.getValue());
                prop.setNote(r.getNote());
                prop.setQrcodeSlice(slice.getId());
                slicePropMapper.insert(prop);
                sliceProps.add(prop);
            }
            QrcodeSliceResult result = QrcodeSliceResult.createBy(slice, sliceProps);
            qrcodeSliceResults.add(result);
        }
        return qrcodeSliceResults;
    }

    @CjTransaction
    @Override
    public void updateQrcodeSliceProperty(String principal, String slice, String propId, String propValue) {
        slicePropMapper.update(slice, propId, propValue);
    }

    @CjTransaction
    @Override
    public QrcodeSliceTemplateResult getQrcodeSliceTemplate(String id) {
        SliceTemplate template = this.sliceTemplateMapper.selectByPrimaryKey(id);
        if (template == null) {
            return null;
        }
        TemplatePropExample example = new TemplatePropExample();
        example.createCriteria().andTemplateEqualTo(id);
        List<TemplateProp> props = this.templatePropMapper.selectByExample(example);
        return QrcodeSliceTemplateResult.createBy(template, props);
    }

    @CjTransaction
    @Override
    public List<QrcodeSliceTemplateResult> pageQrcodeSliceTemplate(int limit, long offset) {
        List<SliceTemplate> list = this.sliceTemplateMapper.page(limit, offset);
        List<QrcodeSliceTemplateResult> results = new ArrayList<>();
        for (SliceTemplate template : list) {
            TemplatePropExample example = new TemplatePropExample();
            example.createCriteria().andTemplateEqualTo(template.getId());
            List<TemplateProp> props = this.templatePropMapper.selectByExample(example);
            QrcodeSliceTemplateResult result = QrcodeSliceTemplateResult.createBy(template, props);
            results.add(result);
        }
        return results;
    }

    @CjTransaction
    @Override
    public List<SliceBatch> pageQrcodeSliceBatch(String principal,int limit, long offset) {
        return sliceBatchMapper.page(principal,limit, offset);
    }

    @CjTransaction
    @Override
    public List<QrcodeSliceResult> pageQrcodeSlice(String principal,int limit, long offset) {
        List<QrcodeSlice> qrcodeSlices = qrcodeSliceMapper.page(principal,limit, offset);
        List<QrcodeSliceResult> list = new ArrayList<>();
        for (QrcodeSlice slice : qrcodeSlices) {
            SlicePropExample example = new SlicePropExample();
            example.createCriteria().andQrcodeSliceEqualTo(slice.getId());
            List<SliceProp> props = slicePropMapper.selectByExample(example);
            QrcodeSliceResult result = QrcodeSliceResult.createBy(slice, props);
            list.add(result);
        }
        return list;
    }

    @CjTransaction
    @Override
    public List<QrcodeSliceResult> pageQrcodeSliceOfBatch(String batchno,String principal, int limit, long offset) {
        List<QrcodeSlice> qrcodeSlices = qrcodeSliceMapper.pageByBatch(batchno,principal, limit, offset);
        List<QrcodeSliceResult> list = new ArrayList<>();
        for (QrcodeSlice slice : qrcodeSlices) {
            SlicePropExample example = new SlicePropExample();
            example.createCriteria().andQrcodeSliceEqualTo(slice.getId());
            List<SliceProp> props = slicePropMapper.selectByExample(example);
            QrcodeSliceResult result = QrcodeSliceResult.createBy(slice, props);
            list.add(result);
        }
        return list;
    }

    @CjTransaction
    @Override
    public QrcodeSliceResult getQrcodeSlice(String slice) {
        QrcodeSlice qrcodeSlice = qrcodeSliceMapper.selectByPrimaryKey(slice);
        if (qrcodeSlice == null) {
            return null;
        }
        SlicePropExample example = new SlicePropExample();
        example.createCriteria().andQrcodeSliceEqualTo(slice);
        List<SliceProp> props = slicePropMapper.selectByExample(example);
        QrcodeSliceResult result = QrcodeSliceResult.createBy(qrcodeSlice, props);
        return result;
    }

    @CjTransaction
    @Override
    public void addQrcodeSliceRecipients(String principal, String absorberid, String qrcodeSlice) throws CircuitException {
        Absorber absorber = getAbsorber(absorberid);
        QrcodeSlice slice = getQrcodeSlice(qrcodeSlice);
        if (absorber == null || slice == null || slice.getState() == 1) {
            CJSystem.logging().info(getClass(), String.format("码片%s不存在或状态为已消费", qrcodeSlice));
            return;
        }
        if (slice.getState() == -1) {
            qrcodeSliceMapper.updateState(slice.getId(), 0);//修改状态为激活态
        }

        //如果是发码人创建的的洇取器，则只放余额洇取人，否则即放余额洇取人又发发码激历洇取人
        if (!absorber.getCreator().equals(slice.getCreator())) {//参与的洇取器则添加发码激历洇取人
            Recipients recipients = new Recipients();
            recipients.setAbsorber(absorberid);
            recipients.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
            recipients.setDesireAmount(0L);
            recipients.setEncourageCode("pubSlice");
            recipients.setEncourageCause("发码");
            recipients.setEncourageBy(slice.getId());
            recipients.setId(new IdWorker().nextId());
            recipients.setPerson(slice.getCreator());
            recipients.setPersonName(slice.getCname());
            recipients.setWeight(new BigDecimal("0.00"));//权重为零，之后消费一个码片则添加一次权重
            addRecipients(recipients);
        }
        //添加余额洇取人及余额
        Recipients recipients = new Recipients();
        recipients.setAbsorber(absorberid);
        recipients.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        recipients.setDesireAmount(0L);
        recipients.setEncourageCode("balance");
        recipients.setEncourageCause("码片待消费");
        recipients.setEncourageBy(slice.getId());
        recipients.setId(new IdWorker().nextId());
        recipients.setPerson(slice.getCreator());
        recipients.setPersonName(slice.getCname());
        recipients.setWeight(new BigDecimal(absorber.getState() == 0 ? "2.0" : "100.0"));
        addRecipients(recipients);
        //添加余额
        RecipientsBalance balance = new RecipientsBalance();
        balance.setId(new IdWorker().nextId());
        balance.setRecipients(recipients.getId());
        balance.setQrcodeSlice(slice.getId());
        balance.setState(0);
        balance.setAmount(new BigDecimal("0.00"));
        recipientsBalanceMapper.insert(balance);
    }

    @CjTransaction
    @Override
    public void consumeQrcodeSlice(String consumer, String nickName, QrcodeSlice qrcodeSlice) throws CircuitException {
        //修改发码人在各个洇取器中的权重，要保证：但不是消费一个码片就修改全部，至到所有码片消费完才全部修改完
        //处理当前码片的洇取人余额，并将洇取人修改为实际的消费者
        //注意码片状态的修改
        updateSlicePublisherWeight(qrcodeSlice);
        doRecipientsBalance(consumer, nickName, qrcodeSlice);
        qrcodeSliceMapper.consume(qrcodeSlice.getId(), consumer);
    }

    private void updateSlicePublisherWeight(QrcodeSlice qrcodeSlice) {
        long countSliceInBatch = countSliceInBatch(qrcodeSlice.getBatchNo());
        long countSliceConsumedInBatch = countConsumedSliceInBatch(qrcodeSlice.getBatchNo());
        if (countSliceConsumedInBatch == countSliceInBatch) {
            return;
        }
        double ratio = (countSliceConsumedInBatch + 1.0) / (countSliceInBatch * 1.0);
        long countRecipientsInSlice = countRecipientsInSlice(qrcodeSlice.getId());
        double demandUpdateRecipients = countRecipientsInSlice * ratio;
        if (demandUpdateRecipients < 1) {
            demandUpdateRecipients = 1;
        }
        //本次需要修改发码人的权重的数量
        long demandUpdateRecipientsCount = (long) demandUpdateRecipients;
        List<Recipients> recipients = recipientsMapper.listRecipientsWeightIsZeroOfEncourageBy("pubSlice", qrcodeSlice.getId(), demandUpdateRecipientsCount);
        for (Recipients r : recipients) {
            Absorber absorber = getAbsorber(r.getAbsorber());
            if (absorber == null) {
                continue;
            }
            updateRecipientsWeights(r.getId(), new BigDecimal(absorber.getState() == 0 ? "2.0" : "100.0"));
        }
    }

    private long countRecipientsInSlice(String slice) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andEncourageByEqualTo(slice).andEncourageCodeEqualTo("pubSlice");
        return recipientsMapper.countByExample(example);
    }

    private long countConsumedSliceInBatch(String batchNo) {
        QrcodeSliceExample example = new QrcodeSliceExample();
        example.createCriteria().andBatchNoEqualTo(batchNo).andStateEqualTo(1);
        return qrcodeSliceMapper.countByExample(example);
    }

    private long countSliceInBatch(String batchNo) {
        QrcodeSliceExample example = new QrcodeSliceExample();
        example.createCriteria().andBatchNoEqualTo(batchNo);
        return qrcodeSliceMapper.countByExample(example);
    }

    private void doRecipientsBalance(String consumer, String nickName, QrcodeSlice qrcodeSlice) throws CircuitException {
        //将洇取人修改为消费者，并将余额转出、修改洇取人余额状态为1（停止接收到余额而采取直接洇取模式）
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andEncourageByEqualTo(qrcodeSlice.getId()).andEncourageCodeEqualTo("balance");
        List<Recipients> recipients = recipientsMapper.selectByExample(example);
        for (Recipients r : recipients) {
            recipientsMapper.consumeSlice(r.getId(), consumer, nickName, "消费码片");
            r.setEncourageCause("消费码片");
            r.setPersonName(nickName);
            r.setPerson(consumer);
            recipientsBalanceMapper.updateState(qrcodeSlice.getId(), r.getId(), 1);
            RecipientsBalance balance = getRecipientsBalnace(r.getId());
            if (balance == null) {
                continue;
            }
            if (balance.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                Absorber absorber = getAbsorber(r.getAbsorber());
                RecipientsBalanceBill bill = new RecipientsBalanceBill();
                bill.setQrcodeSlice(r.getEncourageBy());
                bill.setRecipientsId(r.getId());
                bill.setAmount(balance.getAmount().multiply(new BigDecimal("-1.0")));
                bill.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
                bill.setPerson(r.getPerson());
                bill.setPersonName(r.getPersonName());
                bill.setTitle("码片余额转结");
                bill.setOrder(1);
                bill.setSn(new IdWorker().nextId());
                bill.setAbsorber(r.getAbsorber());
                BigDecimal balanceAmount = balance.getAmount().add(bill.getAmount());
                bill.setBalance(balanceAmount);
                recipientsBalanceBillMapper.insert(bill);
                recipientsBalanceMapper.updateBalance(r.getId(), balanceAmount);
                r.setEncourageCause("码片余额转结");
                RecipientsAbsorbBill abill = new RecipientsAbsorbBill(absorber.getTitle(), r, balance.getAmount(), bill.getSn());
                transToWallet(abill);
            }
        }
    }

    @CjTransaction
    @Override
    public void addRecipientsBalanceBill(Recipients recipients, RecipientsBalance balance, BigDecimal money) {
        RecipientsBalanceBill bill = new RecipientsBalanceBill();
        bill.setQrcodeSlice(recipients.getEncourageBy());
        bill.setRecipientsId(recipients.getId());
        bill.setAmount(money);
        bill.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        bill.setPerson(recipients.getPerson());
        bill.setPersonName(recipients.getPersonName());
        bill.setTitle("洇取到码片余额");
        bill.setOrder(0);
        bill.setSn(new IdWorker().nextId());
        bill.setAbsorber(recipients.getAbsorber());
        BigDecimal nextBalance = balance.getAmount().add(bill.getAmount());
        bill.setBalance(nextBalance);
        recipientsBalanceBillMapper.insert(bill);
        recipientsBalanceMapper.updateBalance(recipients.getId(), nextBalance);
    }

    @CjTransaction
    @Override
    public RecipientsBalance getRecipientsBalnace(String recipientsid) {
        RecipientsBalanceExample example = new RecipientsBalanceExample();
        example.createCriteria().andRecipientsEqualTo(recipientsid);
        List<RecipientsBalance> balances = recipientsBalanceMapper.selectByExample(example);
        if (balances.isEmpty()) {
            return null;
        }
        return balances.get(0);
    }

    @CjTransaction
    @Override
    public void updateRecipientsBalance(String recipientsid, BigDecimal amount) {
        recipientsBalanceMapper.updateBalance(recipientsid, amount);
    }

    private void transToWallet(RecipientsAbsorbBill bill) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/robot/hub.ports")
                .headers(new HashMap() {
                    {
                        put("command", "distribute");
                    }
                }).build();
        byte[] body = new Gson().toJson(bill).getBytes();
        rabbitMQProducer.publish("wallet", properties, body);
    }
}
