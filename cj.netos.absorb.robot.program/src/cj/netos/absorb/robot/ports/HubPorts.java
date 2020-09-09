package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.IHubService;
import cj.netos.absorb.robot.bo.DomainBulletin;
import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.bo.RecipientsSummary;
import cj.netos.absorb.robot.model.*;
import cj.netos.absorb.robot.result.AbsorberHubTailsResult;
import cj.netos.absorb.robot.result.AbsorberResult;
import cj.netos.absorb.robot.util.IdWorker;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.util.StringUtil;
import com.rabbitmq.client.AMQP;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CjService(name = "/hub.ports")
public class HubPorts implements IHubPorts {
    @CjServiceRef
    IHubService hubService;
    @CjServiceSite
    IServiceSite site;

    @CjServiceRef(refByName = "@.rabbitmq.producer.withdrawHubTailsToWallet")
    IRabbitMQProducer rabbitMQProducer;


    @Override
    public Absorber createBalanceAbsorber(ISecuritySession securitySession, String bankid, String title, String category, String proxy, LatLng location, long radius) throws CircuitException {
        if (StringUtil.isEmpty(bankid) || StringUtil.isEmpty(title) || StringUtil.isEmpty(category) || StringUtil.isEmpty(proxy) || location == null) {
            throw new CircuitException("404", "参数为空");
        }
        if (radius < 1) {
            throw new CircuitException("500", "半径不能为0米");
        }
        Absorber absorber = new Absorber();
        absorber.setBankid(bankid);
        absorber.setCategory(category);
        absorber.setCreator(securitySession.principal());
        absorber.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorber.setId(new IdWorker().nextId());
        absorber.setType(2);
        absorber.setProxy(proxy);
        absorber.setTitle(title);
        absorber.setLocation(location.toJson());
        absorber.setRadius(radius);
        absorber.setState(1);
        absorber.setMaxRecipients(0L);
        hubService.createAbsorber(absorber);
        return absorber;
    }

    @Override
    public DomainBulletin getDomainBucket(ISecuritySession securitySession, String bankid) throws CircuitException {
        return hubService.getDomainBulletin(bankid);
    }

    @Override
    public Absorber createSimpleAbsorber(ISecuritySession securitySession, String bankid, String title, long maxRecipients, String category, String proxy) throws CircuitException {
        if (StringUtil.isEmpty(bankid) || StringUtil.isEmpty(title) || StringUtil.isEmpty(category) || StringUtil.isEmpty(proxy)) {
            throw new CircuitException("404", "参数为空");
        }
        Absorber absorber = new Absorber();
        absorber.setBankid(bankid);
        absorber.setCategory(category);
        absorber.setCreator(securitySession.principal());
        absorber.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorber.setId(new IdWorker().nextId());
        absorber.setType(0);
        absorber.setProxy(proxy);
        absorber.setTitle(title);
        absorber.setState(1);
        absorber.setMaxRecipients(maxRecipients);
        hubService.createAbsorber(absorber);

        return absorber;
    }

    @Override
    public Absorber createGeoAbsorber(ISecuritySession securitySession, String bankid, String title, String category, String proxy, LatLng location, long radius) throws CircuitException {
        if (StringUtil.isEmpty(bankid) || StringUtil.isEmpty(title) || StringUtil.isEmpty(category) || StringUtil.isEmpty(proxy) || location == null) {
            throw new CircuitException("404", "参数为空");
        }
        if (radius < 1) {
            throw new CircuitException("500", "半径不能为0米");
        }
        Absorber absorber = new Absorber();
        absorber.setBankid(bankid);
        absorber.setCategory(category);
        absorber.setCreator(securitySession.principal());
        absorber.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorber.setId(new IdWorker().nextId());
        absorber.setType(1);
        absorber.setProxy(proxy);
        absorber.setTitle(title);
        absorber.setLocation(location.toJson());
        absorber.setRadius(radius);
        absorber.setState(1);
        absorber.setMaxRecipients(0L);
        hubService.createAbsorber(absorber);
        return absorber;
    }

    @Override
    public AbsorberResult getAbsorber(ISecuritySession securitySession, String absorberid) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        AbsorberBucket bucket = hubService.getAndInitAbsorbBucket(absorberid);
        return new AbsorberResult(absorber, bucket);
    }

    @Override
    public List<AbsorberResult> pageAbsorber(ISecuritySession securitySession, String bankid, int type, int limit, long offset) throws CircuitException {
        List<Absorber> absorbers = hubService.pageAbsorber(bankid, type, limit, offset);
        List<AbsorberResult> absorberResults = new ArrayList<>();
        for (Absorber absorber : absorbers) {
            AbsorberBucket bucket = hubService.getAndInitAbsorbBucket(absorber.getId());
            absorberResults.add(new AbsorberResult(absorber, bucket));
        }
        return absorberResults;
    }

    @Override
    public void removeAbsorber(ISecuritySession securitySession, String absorberid) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            return;
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", "不能删除他人的洇取器");
        }
        hubService.removeAbsorber(absorberid);
    }

    @Override
    public void addRecipients(ISecuritySession securitySession, String absorberid, String encourageCode, String encourageCause, long desireAmount) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (hubService.existsRecipientsEncourageCode(securitySession.principal(), absorberid, encourageCode)) {
            throw new CircuitException("2000", String.format("洇取器:%s (%s)中已存在收取人及期激励方式:%s。", absorber.getTitle(), absorber.getId(), securitySession.principal()));
        }
        Recipients recipients = new Recipients();
        recipients.setAbsorber(absorberid);
        recipients.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        recipients.setDesireAmount(desireAmount);
        recipients.setEncourageCause(encourageCause);
        recipients.setEncourageCode(encourageCode);
        recipients.setId(new IdWorker().nextId());
        recipients.setPerson(securitySession.principal());
        String weightStr = "";
        switch (encourageCode) {
            case "like":
                weightStr = site.getProperty("hub.absorber.recipients.weight.encourage.like");
                if (StringUtil.isEmpty(weightStr)) {
                    weightStr = "1.00";
                }
                break;
            case "comment":
                weightStr = site.getProperty("hub.absorber.recipients.weight.encourage.comment");
                if (StringUtil.isEmpty(weightStr)) {
                    weightStr = "1.50";
                }
                break;
            default:
                weightStr = "1.00";
                break;
        }
        BigDecimal weight = new BigDecimal(weightStr);
        recipients.setWeight(weight);//默认为1，只有洇取器的创建者才有权调动收件人的权重
        recipients.setPersonName((String) securitySession.property("nickName"));

        hubService.addRecipients(recipients);
    }

    @Override
    public void removeRecipients(ISecuritySession securitySession, String absorberid, String person) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500",String.format("不是创建者:%s",securitySession.principal()));
        }
        hubService.removeRecipients(absorberid,person);
    }

    @Override
    public void addWeightsToRecipients(ISecuritySession securitySession, String absorberid, String person, String encourageCode, BigDecimal weights) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500",String.format("不是创建者:%s",securitySession.principal()));
        }
        hubService.updateRecipientsWeights(absorberid,person,encourageCode,weights);
    }

    @Override
    public TailBill withdrawHubTails(ISecuritySession securitySession, String bankid) throws CircuitException {
        //检查银行的创建者是否有securitySession当事人
        checkWithdrawRights(securitySession, bankid);
        TailBill bill = hubService.withdrawHubTails(securitySession.principal(), bankid);
        if (bill == null) {
            throw new CircuitException("500", "金额不足1分");
        }
        AbsorberHubTailsResult result = new AbsorberHubTailsResult();
        result.setAmount(bill.getAmount().longValue());
        result.setBankid(bill.getBankid());
        result.setCtime(bill.getCtime());
        result.setNote(bill.getNote());
        result.setOrder(bill.getOrder());
        result.setPerson(bill.getPerson());
        result.setSn(bill.getSn());
        result.setPersonName((String) securitySession.property("nickName"));

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/robot/hub.ports")
                .headers(new HashMap() {
                    {
                        put("command", "withrawHubTails");
                    }
                }).build();
        byte[] body = new Gson().toJson(result).getBytes();
        rabbitMQProducer.publish("wallet", properties, body);
        return bill;
    }

    @Override
    public void stopAbsorber(ISecuritySession securitySession, String absorberid, String exitCause) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
        checkWithdrawRights(securitySession, absorber.getBankid());
        hubService.stopAbsorber(absorberid, exitCause);
    }

    @Override
    public void startAbsorber(ISecuritySession securitySession, String absorberid) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
        checkWithdrawRights(securitySession, absorber.getBankid());
        hubService.startAbsorber(absorberid);
    }

    @Override
    public HubTails getHubTails(ISecuritySession securitySession, String bankid) throws CircuitException {
//        checkWithdrawRights(securitySession, bankid);
        return hubService.getAndInitHubTails(bankid);
    }

    private void checkWithdrawRights(ISecuritySession securitySession, String bankid) throws CircuitException {
        Map<String, Object> bankInfo = _call_get_bank(bankid, (String) securitySession.property("accessToken"));
        String creator = (String) bankInfo.get("creator");
        if (!securitySession.principal().equals(creator)) {
            throw new CircuitException("801", "拒绝访问");
        }
    }


    private Map<String, Object> _call_get_bank(String bankid, String accessToken) throws CircuitException {
        OkHttpClient client = (OkHttpClient) site.getService("@.http");
        String portsurl = site.getProperty("rhub.ports.wybank.info");
        String url = String.format("%s?banksn=%s", portsurl, bankid);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Rest-Command", "getWenyBankInfo")
                .addHeader("cjtoken", accessToken)
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
        return new Gson().fromJson(json, HashMap.class);
    }

    @Override
    public List<Recipients> pageRecipients(ISecuritySession securitySession, String absorberid, int limit, long offset) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
//        checkWithdrawRights(securitySession, absorber.getBankid());
        if (absorber.getType() == 0) {
            return hubService.pageRecipients(absorberid, limit, offset);
        }
        return hubService.pageGeoRecipients(absorber, limit, offset);
    }

    @Override
    public List<RecipientsSummary> pageSimpleRecipients(ISecuritySession securitySession, String absorberid, int limit, long offset) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
//        checkWithdrawRights(securitySession, absorber.getBankid());
        if (absorber.getType() != 0) {
            throw new CircuitException("500", "不是简单涸取器");
        }
        return hubService.pageRecipientsSummary(absorberid, limit, offset);
    }

    @Override
    public long countRecipients(ISecuritySession securitySession, String absorberid) throws CircuitException {
        return hubService.countRecipients(absorberid);
    }
}
