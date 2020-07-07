package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.IAbsorberTemplateService;
import cj.netos.absorb.robot.bo.AbsorberRule;
import cj.netos.absorb.robot.bo.AbsorberTemplate;
import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.model.*;
import cj.netos.absorb.robot.result.AbsorberHubTailsResult;
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
public class AbsorberHubPorts implements IAbsorberHubPorts {
    @CjServiceRef
    IAbsorberHubService absorberHubService;
    @CjServiceRef
    IAbsorberTemplateService absorberTemplateService;
    @CjServiceSite
    IServiceSite site;

    @CjServiceRef(refByName = "@.rabbitmq.producer.withdrawHubTailsToWallet")
    IRabbitMQProducer rabbitMQProducer;

    @Override
    public Absorber createSimpleAbsorber(ISecuritySession securitySession, String bankid, String title, String category, String proxy) throws CircuitException {
        if (StringUtil.isEmpty(bankid) || StringUtil.isEmpty(title) || StringUtil.isEmpty(category) || StringUtil.isEmpty(proxy)) {
            throw new CircuitException("404", "参数为空");
        }
        AbsorberTemplate template = absorberHubService.getAbsorberTemplate();
        Absorber absorber = new Absorber();
        absorber.setBankid(bankid);
        absorber.setCategory(category);
        absorber.setCreator(securitySession.principal());
        absorber.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorber.setId(new IdWorker().nextId());
        absorber.setType(0);
        absorber.setProxy(proxy);
        absorber.setTitle(title);
        absorber.setWeight(new BigDecimal("1.00"));//权重由行长进行调整
        absorber.setExitAmount(template.getCategoryExitAmount(category));
        absorber.setExitExpire(template.getCategoryExitExpire(category));
        absorber.setExitTimes(template.getCategoryExitTimes(category));
        absorber.setMaxRecipients(template.getCategoryMaxRecipients(category));
        absorber.setState(0);
        absorber.setCurrentAmount(new BigDecimal("0.00"));
        absorber.setCurrentTimes(0L);
        absorberHubService.createSimpleAbsorber(absorber);

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
        AbsorberTemplate template = absorberHubService.getAbsorberTemplate();
        Absorber absorber = new Absorber();
        absorber.setBankid(bankid);
        absorber.setCategory(category);
        absorber.setCreator(securitySession.principal());
        absorber.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        absorber.setId(new IdWorker().nextId());
        absorber.setType(1);
        absorber.setProxy(proxy);
        absorber.setTitle(title);
        absorber.setWeight(new BigDecimal("1.00"));//权重由行长进行调整
        absorber.setExitAmount(template.getCategoryExitAmount(category));
        absorber.setExitExpire(template.getCategoryExitExpire(category));
        absorber.setExitTimes(template.getCategoryExitTimes(category));
        absorber.setLocation(location.toJson());
        absorber.setRadius(radius);
        absorber.setMaxRecipients(template.getCategoryMaxRecipients(category));
        absorber.setState(0);
        absorber.setCurrentAmount(new BigDecimal("0.00"));
        absorber.setCurrentTimes(0L);
        absorberHubService.createSimpleAbsorber(absorber);
        return absorber;
    }

    @Override
    public Absorber getAbsorber(ISecuritySession securitySession, String absorberid) throws CircuitException {
        return absorberHubService.getAbsorber(absorberid);
    }

    @Override
    public List<Absorber> pageAbsorber(ISecuritySession securitySession, String bankid, int type, int limit, long offset) throws CircuitException {
        return absorberHubService.pageAbsorber(bankid, type, limit, offset);
    }

    @Override
    public void removeAbsorber(ISecuritySession securitySession, String absorberid) throws CircuitException {
        Absorber absorber = getAbsorber(securitySession, absorberid);
        if (absorber == null) {
            return;
        }
        if (!absorber.getCreator().equals(securitySession.principal())) {
            throw new CircuitException("500", "不能删除他人的洇取器");
        }
        absorberHubService.removeAbsorber(absorberid);
    }

    @Override
    public void addRecipients(ISecuritySession securitySession, String absorberid, String encourageCode, String encourageCause, long desireAmount) throws CircuitException {
        Absorber absorber = getAbsorber(securitySession, absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器已不存在。" + absorberid);
        }
        if (absorberHubService.existsRecipientsEncourageCode(securitySession.principal(), absorberid, encourageCode)) {
            throw new CircuitException("2000", String.format("洇取器:%s (%s)中已存在收取人及期激励方式:%s。", absorber.getTitle(), absorber.getId(), securitySession.principal()));
        }
        AbsorberTemplate template = absorberHubService.getAbsorberTemplate();
        Recipients recipients = new Recipients();
        recipients.setAbsorber(absorberid);
        recipients.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        recipients.setDesireAmount(desireAmount);
        recipients.setEncourageCause(encourageCause);
        recipients.setEncourageCode(encourageCode);
        recipients.setId(new IdWorker().nextId());
        recipients.setPerson(securitySession.principal());
        recipients.setWeight(template.getCategoryEncourage(absorber.getCategory(), encourageCode));//默认为1，只有洇取器的创建者才有权调动收件人的权重
        recipients.setPersonName((String) securitySession.property("nickName"));

        absorberHubService.addRecipients(recipients);
    }

    @Override
    public void reloadAbsorberTemplate(ISecuritySession securitySession) throws CircuitException {
        if (!securitySession.roleIn("platform:administrators")) {
            throw new CircuitException("800", "拒绝访问");
        }
        absorberTemplateService.refresh();
    }

    @Override
    public TailBill withdrawHubTails(ISecuritySession securitySession, String bankid) throws CircuitException {
        //检查银行的创建者是否有securitySession当事人
        checkWithdrawRights(securitySession, bankid);
        TailBill bill = absorberHubService.withdrawHubTails(securitySession.principal(), bankid);
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
        Absorber absorber = getAbsorber(securitySession, absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
        checkWithdrawRights(securitySession, absorber.getBankid());
        absorberHubService.stopAbsorber(absorberid, exitCause);
    }

    @Override
    public void startAbsorber(ISecuritySession securitySession, String absorberid) throws CircuitException {
        Absorber absorber = getAbsorber(securitySession, absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
        checkWithdrawRights(securitySession, absorber.getBankid());
        absorberHubService.startAbsorber(absorberid);
    }

    @Override
    public HubTails getHubTails(ISecuritySession securitySession, String bankid) throws CircuitException {
        checkWithdrawRights(securitySession, bankid);
        return absorberHubService.getAndInitHubTails(bankid);
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
    public void adjustWeightOfCategory(ISecuritySession securitySession, String category, BigDecimal weight) throws CircuitException {
        if (!securitySession.roleIn("platform:administrators")) {
            throw new CircuitException("800", "拒绝访问");
        }
        AbsorberRule rule = absorberTemplateService.getTemplate().getCategories().get(category);
        if (rule == null) {
            throw new CircuitException("404", "分类不存在");
        }
        rule.setWeight(weight);
    }

    @Override
    public void adjustBaseWeightOfRecipients(ISecuritySession securitySession, String category, String encourage, BigDecimal weight) throws CircuitException {
        if (!securitySession.roleIn("platform:administrators")) {
            throw new CircuitException("800", "拒绝访问");
        }
        AbsorberRule rule = absorberTemplateService.getTemplate().getCategories().get(category);
        if (rule == null) {
            throw new CircuitException("404", "分类不存在");
        }
        if (!rule.getEncourage().containsKey(encourage)) {
            throw new CircuitException("404", "激励代码不存在：" + encourage);
        }
        rule.getEncourage().put(encourage, weight);
    }

    @Override
    public List<Recipients> pageRecipients(ISecuritySession securitySession, String absorberid, int limit, long offset) throws CircuitException {
        Absorber absorber = getAbsorber(securitySession, absorberid);
        if (absorber == null) {
            throw new CircuitException("404", "洇取器不存在");
        }
        checkWithdrawRights(securitySession, absorber.getBankid());
        if (absorber.getType() == 0) {
            return absorberHubService.pageRecipients(absorberid, limit, offset);
        }
        return absorberHubService.pageGeoRecipients(absorber, limit, offset);
    }
}
