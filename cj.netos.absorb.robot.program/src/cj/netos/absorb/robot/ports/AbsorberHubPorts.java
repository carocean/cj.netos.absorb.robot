package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.IAbsorberTemplateService;
import cj.netos.absorb.robot.bo.AbsorberTemplate;
import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.Recipients;
import cj.netos.absorb.robot.util.IdWorker;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.ultimate.util.StringUtil;

import java.math.BigDecimal;
import java.util.List;

@CjService(name = "/hub.ports")
public class AbsorberHubPorts implements IAbsorberHubPorts {
    @CjServiceRef
    IAbsorberHubService absorberHubService;
    @CjServiceRef
    IAbsorberTemplateService absorberTemplateService;

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
        if (absorberHubService.existsRecipients(securitySession.principal(), absorberid)) {
            throw new CircuitException("500", String.format("洇取器:%s (%s)中已存在收取人:%s。", absorber.getTitle(), absorber.getId(), securitySession.principal()));
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
}
