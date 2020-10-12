package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.IHubService;
import cj.netos.absorb.robot.bo.DomainBulletin;
import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.bo.QrcodeSliceTemplateBO;
import cj.netos.absorb.robot.bo.RecipientsSummary;
import cj.netos.absorb.robot.model.*;
import cj.netos.absorb.robot.result.AbsorberHubTailsResult;
import cj.netos.absorb.robot.result.AbsorberResult;
import cj.netos.absorb.robot.result.QrcodeSliceResult;
import cj.netos.absorb.robot.result.QrcodeSliceTemplateResult;
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
import java.util.*;

@CjService(name = "/hub.ports")
public class HubPorts implements IHubPorts {
    @CjServiceRef
    IHubService hubService;
    @CjServiceSite
    IServiceSite site;

    @CjServiceRef(refByName = "@.rabbitmq.producer.withdrawHubTailsToWallet")
    IRabbitMQProducer rabbitMQProducer;

    private void checkAdministratorRights(ISecuritySession securitySession) throws CircuitException {
        if (!securitySession.roleIn("platform:administrators")) {
            throw new CircuitException("800", "拒绝访问");
        }
    }

    @Override
    public Absorber createBalanceAbsorber(ISecuritySession securitySession, String bankid, String title, int usage, String absorbabler, LatLng location, long radius) throws CircuitException {
        if (StringUtil.isEmpty(bankid) || StringUtil.isEmpty(title) || StringUtil.isEmpty(absorbabler) || location == null) {
            throw new CircuitException("404", "参数为空");
        }
        if (radius < 1) {
            throw new CircuitException("500", "半径不能为0米");
        }
        Absorber absorber = new Absorber();
        absorber.setBankid(bankid);
        absorber.setUsage(usage);
        absorber.setAbsorbabler(absorbabler);
        absorber.setCreator(securitySession.principal());
        absorber.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorber.setId(new IdWorker().nextId());
        absorber.setType(2);
        absorber.setTitle(title);
        absorber.setLocation(location.toJson());
        absorber.setRadius(radius);
        absorber.setState(1);
        absorber.setMaxRecipients(0L);
        hubService.createAbsorber(absorber);
        return absorber;
    }

    @Override
    public Absorber createSimpleAbsorber(ISecuritySession securitySession, String bankid, String title, long maxRecipients, int usage, String absorbabler) throws CircuitException {
        if (StringUtil.isEmpty(bankid) || StringUtil.isEmpty(title) || StringUtil.isEmpty(absorbabler)) {
            throw new CircuitException("404", "参数为空");
        }
        Absorber absorber = new Absorber();
        absorber.setBankid(bankid);
        absorber.setUsage(usage);
        absorber.setAbsorbabler(absorbabler);
        absorber.setCreator(securitySession.principal());
        absorber.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorber.setId(new IdWorker().nextId());
        absorber.setType(0);
        absorber.setTitle(title);
        absorber.setState(1);
        absorber.setMaxRecipients(maxRecipients);
        hubService.createAbsorber(absorber);

        return absorber;
    }

    @Override
    public Absorber createGeoAbsorber(ISecuritySession securitySession, String bankid, String title, int usage, String absorbabler, LatLng location, long radius) throws CircuitException {
        if (StringUtil.isEmpty(bankid) || StringUtil.isEmpty(title) || StringUtil.isEmpty(absorbabler) || location == null) {
            throw new CircuitException("404", "参数为空");
        }
        if (radius < 1) {
            throw new CircuitException("500", "半径不能为0米");
        }
        Absorber absorber = new Absorber();
        absorber.setBankid(bankid);
        absorber.setUsage(usage);
        absorber.setAbsorbabler(absorbabler);
        absorber.setCreator(securitySession.principal());
        absorber.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorber.setId(new IdWorker().nextId());
        absorber.setType(1);
        absorber.setTitle(title);
        absorber.setLocation(location.toJson());
        absorber.setRadius(radius);
        absorber.setState(1);
        absorber.setMaxRecipients(0L);
        hubService.createAbsorber(absorber);
        return absorber;
    }

    @Override
    public void updateAbsorberLocation(ISecuritySession securitySession, String absorberid, LatLng location) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            return;
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", "不是本人");
        }
        hubService.updateAbsorberLocation(absorberid, location);
    }

    @Override
    public void updateAbsorberRadius(ISecuritySession securitySession, String absorberid, long radius) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            return;
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", "不是本人");
        }
        hubService.updateAbsorberRadius(absorberid, radius);
    }

    @Override
    public boolean isBindingsAbsorbabler(ISecuritySession securitySession, String absorberid, String absorbabler) throws CircuitException {
        return hubService.isBindingsAbsorbabler(absorberid, absorbabler);
    }

    @Override
    public void bindAbsorbabler(ISecuritySession securitySession, String absorberid, String absorbabler) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            return;
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", "不是本人");
        }
        hubService.bindAbsorbabler(absorberid, absorbabler);
    }

    @Override
    public void unbindAbsorbabler(ISecuritySession securitySession, String absorberid) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            return;
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", "不是本人");
        }
        hubService.unbindAbsorbabler(absorberid);
    }

    @Override
    public DomainBulletin getDomainBucket(ISecuritySession securitySession, String bankid) throws CircuitException {
        return hubService.getDomainBulletin(bankid);
    }


    @Override
    public AbsorberResult getAbsorber(ISecuritySession securitySession, String absorberid) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        AbsorberBucket bucket = hubService.getAndInitAbsorbBucket(absorberid);
        return new AbsorberResult(absorber, bucket);
    }

    @Override
    public AbsorberResult getAbsorberByAbsorbabler(ISecuritySession securitySession, String absorbabler) throws CircuitException {
        Absorber absorber = hubService.getAbsorberByAbsorbabler(absorbabler);
        if (absorber == null) {
            return null;
        }
        AbsorberBucket bucket = hubService.getAndInitAbsorbBucket(absorber.getId());
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
    public List<AbsorberResult> pageMyAbsorber(ISecuritySession securitySession, int type, int limit, long offset) throws CircuitException {
        List<Absorber> absorbers = hubService.pageMyAbsorber(securitySession.principal(), type, limit, offset);
        List<AbsorberResult> absorberResults = new ArrayList<>();
        for (Absorber absorber : absorbers) {
            AbsorberBucket bucket = hubService.getAndInitAbsorbBucket(absorber.getId());
            absorberResults.add(new AbsorberResult(absorber, bucket));
        }
        return absorberResults;
    }

    @Override
    public List<AbsorberResult> pageMyAbsorberByUsage(ISecuritySession securitySession, int usage, int limit, long offset) throws CircuitException {
        List<Absorber> absorbers = hubService.pageMyAbsorberByUsage(securitySession.principal(), usage, limit, offset);
        List<AbsorberResult> absorberResults = new ArrayList<>();
        for (Absorber absorber : absorbers) {
            AbsorberBucket bucket = hubService.getAndInitAbsorbBucket(absorber.getId());
            absorberResults.add(new AbsorberResult(absorber, bucket));
        }
        return absorberResults;
    }

    @Override
    public List<AbsorberResult> pageJioninAbsorberByUsage(ISecuritySession securitySession, int usage, int limit, long offset) throws CircuitException {
        List<Absorber> absorbers = hubService.pageJioninAbsorberByUsage(securitySession.principal(), usage, limit, offset);
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
        _addRecipients(absorberid, securitySession.principal(), (String) securitySession.property("nickName"), encourageCode, encourageCause, desireAmount);
    }

    private void _addRecipients(String absorberid, String person, String personName, String encourageCode, String encourageCause, long desireAmount) throws CircuitException {
        Recipients recipients = new Recipients();
        recipients.setAbsorber(absorberid);
        recipients.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        recipients.setDesireAmount(desireAmount);
        recipients.setEncourageCause(encourageCause);
        recipients.setEncourageCode(encourageCode);
        recipients.setId(new IdWorker().nextId());
        recipients.setPerson(person);
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
        recipients.setPersonName(personName);

        hubService.addRecipients(recipients);
    }

    @Override
    public void addRecipients2(ISecuritySession securitySession, String absorberid, String person, String nickName, String encourageCode, String encourageCause, long desireAmount) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (hubService.existsRecipientsEncourageCode(person, absorberid, encourageCode)) {
            throw new CircuitException("2000", String.format("洇取器:%s (%s)中已存在收取人及期激励方式:%s。", absorber.getTitle(), absorber.getId(), securitySession.principal()));
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("801", "非洇取器创建者,拒绝该问。");
        }
        _addRecipients(absorberid, person, nickName, encourageCode, encourageCause, desireAmount);
    }

    @Override
    public void addRecipients3(ISecuritySession securitySession, String absorberid, String encourageCode, String encourageCause, long desireAmount) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (hubService.existsRecipientsEncourageCode(securitySession.principal(), absorberid, encourageCode)) {
            throw new CircuitException("2000", String.format("洇取器:%s (%s)中已存在收取人及期激励方式:%s。", absorber.getTitle(), absorber.getId(), securitySession.principal()));
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("801", "非洇取器创建者,拒绝该问。");
        }
        _addRecipients(absorberid, securitySession.principal(), (String) securitySession.property("nickName"), encourageCode, encourageCause, desireAmount);
    }

    @Override
    public void removeRecipients(ISecuritySession securitySession, String absorberid, String person) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", String.format("不是创建者:%s", securitySession.principal()));
        }
        hubService.removeRecipients(absorberid, person);
    }

    @Override
    public boolean existsRecipients(ISecuritySession securitySession, String absorberid, String person) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            return false;
        }
        if (absorber.getType() != 0) {
            List<Recipients> recipients = hubService.getAroundLocationByPerson(absorber, person);
            if (!recipients.isEmpty()) {
                return true;
            }
        }
        return hubService.existsRecipients(person, absorberid);
    }

    @Override
    public boolean existsRecipients2(ISecuritySession securitySession, String absorberid, String person, String encourageCode) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            return false;
        }
        return hubService.existsRecipients2(absorberid, person, encourageCode);
    }

    @Override
    public void removeRecipients2(ISecuritySession securitySession, String absorberid, String person, String encourageCode) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", String.format("不是创建者:%s", securitySession.principal()));
        }
        hubService.removeRecipients2(absorberid, person, encourageCode);
    }

    @Override
    public void removeRecipients3(ISecuritySession securitySession, String absorberid, String encourageCode) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        hubService.removeRecipients2(absorberid, securitySession.principal(), encourageCode);
    }

    @Override
    public void updateMaxRecipients(ISecuritySession securitySession, String absorberid, long maxRecipients) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", String.format("不是创建者:%s", securitySession.principal()));
        }
        hubService.updateMaxRecipients(absorberid, maxRecipients);
    }

    @Override
    public void addWeightsToRecipients(ISecuritySession securitySession, String absorberid, String person, String encourageCode, BigDecimal weights) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", String.format("不是创建者:%s", securitySession.principal()));
        }
        hubService.updateRecipientsWeights(absorberid, person, encourageCode, weights);
    }

    @Override
    public void updateRecipientsWeights(ISecuritySession securitySession, String recipientsId, BigDecimal weights) throws CircuitException {
        Recipients recipients = hubService.getRecipients(recipientsId);
        if (recipients == null) {
            throw new CircuitException("404", "洇取人标识不存在。" + recipientsId);
        }
        Absorber absorber = hubService.getAbsorber(recipients.getAbsorber());
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + recipients.getAbsorber());
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", String.format("不是创建者:%s", securitySession.principal()));
        }
        hubService.updateRecipientsWeights(recipientsId, weights);
    }

    @Override
    public void addCommentWeightsOfRecipients(ISecuritySession securitySession, String absorberid, String encourageCode) throws CircuitException {
        hubService.addCommentWeightsOfRecipients(absorberid, securitySession.principal(), encourageCode);
    }

    @Override
    public boolean subCommentWeightOfRecipients(ISecuritySession securitySession, String absorberid, String encourageCode) throws CircuitException {
        return hubService.subCommentWeightOfRecipients(absorberid, securitySession.principal(), encourageCode);
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
        if (!absorber.getCreator().equals(securitySession.principal())) {
            checkWithdrawRights(securitySession, absorber.getBankid());
        }
        hubService.stopAbsorber(absorberid, exitCause);
    }

    @Override
    public void startAbsorber(ISecuritySession securitySession, String absorberid) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            checkWithdrawRights(securitySession, absorber.getBankid());
        }
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
    public List<Recipients> pageGeoRecipients(ISecuritySession securitySession, String absorberid, int limit, long offset) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
//        checkWithdrawRights(securitySession, absorber.getBankid());
        if (absorber.getType() == 0) {
            return hubService.pageSimpleRecipients(absorberid, limit, offset);
        }
        return hubService.pageGeoRecipients(absorber, limit, offset);
    }

    @Override
    public List<Recipients> pageRecipients(ISecuritySession securitySession, String absorberid, int limit, long offset) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
        List<Recipients> recipients = new ArrayList<>();
        if (absorber.getType() != 0) {
            List<Recipients> geoRecipients = hubService.pageGeoRecipients(absorber, limit, offset);
            if (!geoRecipients.isEmpty()) {
                recipients.addAll(geoRecipients);
            }
        }

        List<Recipients> simpleRecipients = hubService.pageSimpleRecipients(absorberid, limit, offset);
        if (!simpleRecipients.isEmpty()) {
            recipients.addAll(simpleRecipients);
        }
        return recipients;
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
    public List<Recipients> pageSimpleRecipientsOnlyMe(ISecuritySession securitySession, String absorberid, int limit, long offset) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
//        checkWithdrawRights(securitySession, absorber.getBankid());
        if (absorber.getType() != 0) {
            throw new CircuitException("500", "不是简单涸取器");
        }
        return hubService.pageSimpleRecipientsByPerson(absorberid, securitySession.principal(), limit, offset);
    }

    @Override
    public List<Recipients> pageRecipientsOnlyMe(ISecuritySession securitySession, String absorberid, int limit, long offset) throws CircuitException {
        Absorber absorber = hubService.getAbsorber(absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
        List<Recipients> recipientsList = new ArrayList<>();
        if (absorber.getType() != 0) {
            List<Recipients> geoRecipients = hubService.getAroundLocationByPerson(absorber, securitySession.principal());
            recipientsList.addAll(geoRecipients);
        }
        List<Recipients> simples = hubService.pageSimpleRecipientsByPerson(absorberid, securitySession.principal(), limit, offset);
        recipientsList.addAll(simples);
        return recipientsList;
    }

    @Override
    public long countRecipients(ISecuritySession securitySession, String absorberid) throws CircuitException {
        return hubService.countRecipients(absorberid);
    }

    @Override
    public void configQrcodeSliceTemplate(ISecuritySession securitySession, List<QrcodeSliceTemplateBO> templates) throws CircuitException {
        if (templates == null) {
            throw new CircuitException("404", "参数templates 为空");
        }
        checkAdministratorRights(securitySession);
        hubService.configQrcodeSliceTemplate(templates);
    }


    @Override
    public List<QrcodeSliceResult> createQrcodeSlice(ISecuritySession securitySession, String template, long expire, LatLng location, long radius, String originAbsorber, String originPerson, int count, String note) throws CircuitException {
        if (StringUtil.isEmpty(template)) {
            throw new CircuitException("404", "参数template 为空");
        }
        if (location == null) {
            throw new CircuitException("404", "参数location 为空");
        }
        if (count < 1) {
            throw new CircuitException("404", "参数 count<1");
        }
        QrcodeSliceTemplateResult sliceTemplate = hubService.getQrcodeSliceTemplate(template);
        if (sliceTemplate == null) {
            throw new CircuitException("404", "不存在模板:" + template);
        }
        if (hubService.cannotCreateQrocdeSlice(securitySession.principal())) {
            throw new CircuitException("500", String.format("用户%s下存在未消费的码片，必须所有码片消费完才能生成新码片", securitySession.principal()));
        }
        return hubService.createQrcodeSlice(securitySession.principal(), (String) securitySession.property("nickName"), sliceTemplate, expire, location, radius, originAbsorber, originPerson, count, note);
    }

    @Override
    public void updateQrcodeSliceProperty(ISecuritySession securitySession, String slice, String propId, String propValue) throws CircuitException {
        hubService.updateQrcodeSliceProperty(securitySession.principal(), slice, propId, propValue);
    }

    @Override
    public QrcodeSliceTemplateResult getQrcodeSliceTemplate(ISecuritySession securitySession, String id) throws CircuitException {
        return hubService.getQrcodeSliceTemplate(id);
    }

    @Override
    public List<QrcodeSliceTemplateResult> pageQrcodeSliceTemplate(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return hubService.pageQrcodeSliceTemplate(limit, offset);
    }

    @Override
    public List<SliceBatch> pageQrcodeSliceBatch(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return hubService.pageQrcodeSliceBatch(limit, offset);
    }

    @Override
    public List<QrcodeSliceResult> pageQrcodeSlice(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return hubService.pageQrcodeSlice(limit, offset);
    }

    @Override
    public QrcodeSliceResult getQrcodeSlice(ISecuritySession securitySession, String slice) throws CircuitException {
        return hubService.getQrcodeSlice(slice);
    }

    @Override
    public List<QrcodeSliceResult> pageQrcodeSliceOfBatch(ISecuritySession securitySession, String batchno, int limit, long offset) throws CircuitException {
        return hubService.pageQrcodeSliceOfBatch(batchno, limit, offset);
    }

    @Override
    public void addQrcodeSliceRecipients(ISecuritySession securitySession, String absorberid, String qrcodeSlice) throws CircuitException {
        if (hubService.existsPubingSliceRecipients(securitySession.principal(), absorberid)) {
            return;
        }
        hubService.addQrcodeSliceRecipients(securitySession.principal(), absorberid, qrcodeSlice);
    }

    @Override
    public boolean existsPubSliceRecipients(ISecuritySession securitySession, String absorberid) throws CircuitException {
        return hubService.existsPubingSliceRecipients(securitySession.principal(), absorberid);
    }

    @Override
    public void consumeQrcodeSlice(ISecuritySession securitySession, String qrcodeSlice) throws CircuitException {
        QrcodeSlice slice = hubService.getQrcodeSlice(qrcodeSlice);
        if (slice == null) {
            throw new CircuitException("404", String.format("码片不存在:%s", qrcodeSlice));
        }
        if (slice.getState() == 1) {
            throw new CircuitException("500", String.format("码片已消费:%s", qrcodeSlice));
        }
        hubService.consumeQrcodeSlice(securitySession.principal(), (String) securitySession.property("nickName"), slice);
    }
}
