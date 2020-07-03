package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.HubTails;
import cj.netos.absorb.robot.model.Recipients;
import cj.netos.absorb.robot.model.TailBill;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.math.BigDecimal;
import java.util.List;

@CjOpenports(usage = "洇取器集成器")
public interface IAbsorberHubPorts extends IOpenportService {

    @CjOpenport(usage = "创建一个简单的洇取器")
    Absorber createSimpleAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid,
            @CjOpenportParameter(usage = "洇取器名称\n" +
                    "一般以proxy对应对象的名称命名", name = "title") String title,
            @CjOpenportParameter(usage = "分类，如：喷泉、店、平聊、网流地微中文章、追链中的文章等等，以英文代码表示", name = "category") String category,
            @CjOpenportParameter(usage = "代表的对象的标识，如具体的文章标识，具体的实体店标识、具体的金证喷泉标识等\n" +
                    "它与category对应，category说明是哪类实体\n" +
                    "代表的对象如果是以地址表示的则指向其地址，没有的可为空", name = "proxy") String proxy
    ) throws CircuitException;

    @CjOpenport(usage = "创建一个地理洇取器")
    Absorber createGeoAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid,
            @CjOpenportParameter(usage = "洇取器名称\n" +
                    "一般以proxy对应对象的名称命名", name = "title") String title,
            @CjOpenportParameter(usage = "分类，如：喷泉、店、平聊、网流地微中文章、追链中的文章等等，以英文代码表示", name = "category") String category,
            @CjOpenportParameter(usage = "代表的对象的标识，如具体的文章标识，具体的实体店标识、具体的金证喷泉标识等\n" +
                    "它与category对应，category说明是哪类实体\n" +
                    "代表的对象如果是以地址表示的则指向其地址，没有的可为空", name = "proxy") String proxy,
            @CjOpenportParameter(usage = "位置，经纬度json，格式为：{\"latitude\":%s,\"longitude\":%s}", name = "location") LatLng location,
            @CjOpenportParameter(usage = "半径，单位米", name = "radius") long radius
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇取器")
    Absorber getAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;

    @CjOpenport(usage = "分页洇取器")
    List<Absorber> pageAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid,
            @CjOpenportParameter(usage = "洇取器类型。0指所有洇取器；1指非地理洇取器；2指地理洇取器", name = "type") int type,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "移除指定的洇取器")
    void removeAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;

    @CjOpenport(usage = "添加自已为洇金收取人")
    void addRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "激励代码，如\n" +
                    "great\n" +
                    "like\n" +
                    "ingeo\n" +
                    "等等", name = "encourageCode") String encourageCode,
            @CjOpenportParameter(usage = "激励原因：如他点了赞或做了评论，或在地圈等等原因", name = "encourageCause") String encourageCause,
            @CjOpenportParameter(usage = "期望的激励金，如果达到激励金额则移除\n" +
                    "激励金如果为0表示永远洇取洇金", name = "desireAmount") long desireAmount
    ) throws CircuitException;

    @CjOpenport(usage = "重新加载洇取器模板，必须平台管理员权限")
    void reloadAbsorberTemplate(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "提取尾金到我的钱包零钱，只有行主的主权账号具有提现权现")
    TailBill withdrawHubTails(ISecuritySession securitySession,
                              @CjOpenportParameter(usage = "行号", name = "bankid") String bankid
    ) throws CircuitException;

    @CjOpenport(usage = "获取尾金账户，只有行主有权限")
    HubTails getHubTails(ISecuritySession securitySession, @CjOpenportParameter(usage = "行号", name = "bankid") String bankid) throws CircuitException;

    @CjOpenport(usage = "停用洇取器,仅行主有权限")
    void stopAbsorber(ISecuritySession securitySession,
                      @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
                      @CjOpenportParameter(usage = "退出原因", name = "exitCause") String exitCause
    ) throws CircuitException;

    @CjOpenport(usage = "调整洇取器分类的权重基数,需要平台管理员权限")
    void adjustWeightOfCategory(ISecuritySession securitySession,
                                @CjOpenportParameter(usage = "洇取器分类代码", name = "category") String category,
                                @CjOpenportParameter(usage = "权重", name = "weight") BigDecimal weight
    ) throws CircuitException;

    @CjOpenport(usage = "调整洇取器分类下洇取人激励类别的权重,需要平台管理员权限")
    void adjustBaseWeightOfRecipients(ISecuritySession securitySession,
                                      @CjOpenportParameter(usage = "洇取器分类代码", name = "category") String category,
                                      @CjOpenportParameter(usage = "激励代码，如：like,comment等等", name = "encourage") String encourage,
                                      @CjOpenportParameter(usage = "权重", name = "weight") BigDecimal weight
    ) throws CircuitException;

    @CjOpenport(usage = "分页洇取器，支持地理洇取器")
    List<Recipients> pageRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    )throws CircuitException;


}
