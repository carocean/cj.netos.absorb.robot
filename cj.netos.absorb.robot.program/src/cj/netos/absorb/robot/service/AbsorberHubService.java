package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.*;
import cj.netos.absorb.robot.bo.AbsorberTemplate;
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
import cj.studio.openport.util.Encript;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.gson2.com.google.gson.reflect.TypeToken;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
    @CjServiceRef
    IAbsorberTemplateService absorberTemplateService;
    @CjServiceSite
    IServiceSite site;

    @CjTransaction
    @Override
    public AbsorberTemplate getAbsorberTemplate() {
        return absorberTemplateService.getTemplate();
    }

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
    public boolean existsRecipientsEncourageCode(String principal, String absorberid, String encourageCode) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(principal).andEncourageCodeEqualTo(encourageCode);
        return recipientsMapper.countByExample(example) > 0;
    }

    @CjTransaction
    @Override
    public void addWeightToRecipients(String principal, String absorberid) {
        RecipientsExample example = new RecipientsExample();
        example.createCriteria().andAbsorberEqualTo(absorberid).andPersonEqualTo(principal).andAbsorberEqualTo(absorberid);
        List<Recipients> list = recipientsMapper.selectByExample(example);
        if (list.isEmpty()) {
            return;
        }
        Recipients recipients = list.get(0);
        BigDecimal weight = recipients.getWeight();
        weight = weight.add(new BigDecimal("0.5"));
        recipientsMapper.updateWeight(recipients.getId(), weight);
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
            recipients.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
            recipients.setPerson(bo.getCreator());
            recipients.setPersonName(bo.getTitle());
            recipients.setEncourageCode("enter");
            recipients.setEncourageCause("进圈");
            recipients.setDesireAmount(0L);
            recipients.setAbsorber(absorber.getId());
            recipients.setWeight(weight);//距中心位置作为权重
            recipientsList.add(recipients);

            totalWeightsOfRecipients = totalWeightsOfRecipients.add(weight);
        }
        return recipientsList;
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
    public TailBill withdrawHubTails(String principal, String bankid) {
        HubTails hubTails = getAndInitHubTails(bankid);
        BigDecimal tails = hubTails.getTailAdmount();
        BigDecimal amount = tails.setScale(0, RoundingMode.DOWN);//舍掉尾数即可提金额
        if (amount.compareTo(new BigDecimal("0.0")) <= 0) {
            return null;
        }
        TailBill bill = new TailBill();
        bill.setPerson(principal);
        bill.setAmount(amount);
        bill.setBalance(tails.subtract(amount));
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
        if (result instanceof BankWithdrawResult) {
            BankWithdrawResult bankWithdrawResult = (BankWithdrawResult) result;
            record.setRefsn(bankWithdrawResult.getOutTradeSn());
        }
        if (result instanceof InvestRecord) {
            InvestRecord investRecord = (InvestRecord) result;
            record.setRefsn(investRecord.getSn());
        }
        record.setSn(new IdWorker().nextId());
        recipientsRecordMapper.insert(record);

        return new RecipientsAbsorbBill(absorber, recipients, money, record.getSn());
    }

    @CjTransaction
    @Override
    public long getMaxRecipientsCount(Absorber absorber) {
        return absorber.getMaxRecipients() == null ? Long.valueOf(site.getProperty("hub.absorber.naxRecipients")) : absorber.getMaxRecipients();
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
    public void updateAbsorberCurrent(String absorber, long currentTimes, BigDecimal currentAmount) {
        absorberMapper.updateCurrent(absorber, currentTimes, currentAmount);
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
    public void updateAbsorberWeight(String absorberid, BigDecimal weight) {
        absorberMapper.updateWeight(absorberid, weight);
    }

}
