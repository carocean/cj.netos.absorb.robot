package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.bo.DomainBulletin;
import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.bo.RecipientsSummary;
import cj.netos.absorb.robot.model.*;
import cj.netos.absorb.robot.result.AbsorberResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.math.BigDecimal;
import java.util.List;

@CjOpenports(usage = "洇取器集成器")
public interface IHubPorts extends IOpenportService {

    @CjOpenport(usage = "获取域公告板")
    DomainBulletin getDomainBucket(ISecuritySession securitySession,
                                   @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid
    ) throws CircuitException;

    @CjOpenport(usage = "创建一个简单的洇取器。多用于网流管道")
    Absorber createSimpleAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid,
            @CjOpenportParameter(usage = "洇取器名称\n" +
                    "一般以absorbabler对应对象的名称命名", name = "title") String title,
            @CjOpenportParameter(usage = "人数上限,0为无限制", name = "maxRecipients") long maxRecipients,
            @CjOpenportParameter(usage = "用途：\n" +
                    "0网流管道\n" +
                    "2街道", name = "usage") int usage,
            @CjOpenportParameter(usage = "可洇取物。管道和感知器以type/id格式表示，其它为id", name = "absorbabler") String absorbabler
    ) throws CircuitException;

    @CjOpenport(usage = "创建一个地理洇取器，多用于地理感知器")
    Absorber createGeoAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid,
            @CjOpenportParameter(usage = "洇取器名称\n" +
                    "一般以absorbabler对应对象的名称命名", name = "title") String title,
            @CjOpenportParameter(usage = "用途：\n" +
                    "1地理感知器\n" +
                    "3金证喷泉", name = "usage") int usage,
            @CjOpenportParameter(usage = "可洇取物。管道和感知器以type/id格式表示，其它为id", name = "absorbabler") String absorbabler,
            @CjOpenportParameter(usage = "位置，经纬度json，格式为：{\"latitude\":%s,\"longitude\":%s}", name = "location") LatLng location,
            @CjOpenportParameter(usage = "半径，单位米", name = "radius") long radius
    ) throws CircuitException;

    @CjOpenport(usage = "创建一个余额洇取器，专用于元宝")
    Absorber createBalanceAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid,
            @CjOpenportParameter(usage = "洇取器名称\n" +
                    "一般以absorbabler对应对象的名称命名", name = "title") String title,
            @CjOpenportParameter(usage = "用途：\n" +
                    "4抢元宝", name = "usage") int usage,
            @CjOpenportParameter(usage = "可洇取物。管道和感知器以type/id格式表示，其它为id", name = "absorbabler") String absorbabler,
            @CjOpenportParameter(usage = "位置，经纬度json，格式为：{\"latitude\":%s,\"longitude\":%s}", name = "location") LatLng location,
            @CjOpenportParameter(usage = "半径，单位米", name = "radius") long radius
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇取器")
    AbsorberResult getAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;


    @CjOpenport(usage = "按可洇取物获取洇取器")
    AbsorberResult getAbsorberByAbsorbabler(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "可洇取物", name = "absorbabler") String absorbabler
    ) throws CircuitException;

    @CjOpenport(usage = "分页洇取器")
    List<AbsorberResult> pageAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid,
            @CjOpenportParameter(usage = "洇取器类型。-1指所有洇取器；0指简单洇取器；2指地理洇取器；3余额洇取器", name = "type") int type,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "移除指定的洇取器")
    void removeAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;

    @CjOpenport(usage = "更新指定的洇取器的位置")
    void updateAbsorberLocation(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "位置，经纬度json，格式为：{\"latitude\":%s,\"longitude\":%s}", name = "location") LatLng location
    ) throws CircuitException;

    @CjOpenport(usage = "更新指定的洇取器半径")
    void updateAbsorberRadius(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "半径，单位米", name = "radius") long radius
    ) throws CircuitException;

    @CjOpenport(usage = "判断指定的洇取物是否已被指定洇取器绑定")
    boolean isBindingsAbsorbabler(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "可洇取物。管道和感知器以type/id格式表示，其它为id", name = "absorbabler") String absorbabler
    ) throws CircuitException;

    @CjOpenport(usage = "绑定指定的洇取物到指定洇取器")
    void bindAbsorbabler(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "可洇取物。管道和感知器以type/id格式表示，其它为id", name = "absorbabler") String absorbabler
    ) throws CircuitException;

    @CjOpenport(usage = "解除指定的洇取物和指定洇取器的绑定")
    void unbindAbsorbabler(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;

    @CjOpenport(usage = "添加自已为洇金收取人")
    void addRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "激励代码，如\n" +
                    "like\n" +
                    "comment\n" +
                    "ingeo\n" +
                    "等等", name = "encourageCode") String encourageCode,
            @CjOpenportParameter(usage = "激励原因：如他点了赞或做了评论，或在地圈等等原因", name = "encourageCause") String encourageCause,
            @CjOpenportParameter(usage = "期望的激励金，如果达到激励金额则移除\n" +
                    "激励金如果为0表示永远洇取洇金", name = "desireAmount") long desireAmount
    ) throws CircuitException;

    @CjOpenport(usage = "添加自已为洇金收取人。仅限洇取器主人才有此权限")
    void addRecipients2(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "公众", name = "person") String person,
            @CjOpenportParameter(usage = "公众昵称", name = "nickName") String nickName,
            @CjOpenportParameter(usage = "激励代码，如\n" +
                    "like\n" +
                    "comment\n" +
                    "ingeo\n" +
                    "pull-in" +
                    "等等", name = "encourageCode") String encourageCode,
            @CjOpenportParameter(usage = "激励原因：如他点了赞或做了评论，或在地圈等等原因", name = "encourageCause") String encourageCause,
            @CjOpenportParameter(usage = "期望的激励金，如果达到激励金额则移除\n" +
                    "激励金如果为0表示永远洇取洇金", name = "desireAmount") long desireAmount
    ) throws CircuitException;

    @CjOpenport(usage = "是否存在洇取人")
    boolean existsRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "成员", name = "person") String person
    ) throws CircuitException;

    @CjOpenport(usage = "是否存在洇取人")
    boolean existsRecipients2(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "成员", name = "person") String person,
            @CjOpenportParameter(usage = "激励代码，如\n" +
                    "like\n" +
                    "comment\n" +
                    "ingeo\n" +
                    "pull-in" +
                    "等等", name = "encourageCode") String encourageCode
    ) throws CircuitException;

    @CjOpenport(usage = "移除洇取人。只有洇取器的创建人才有权限")
    void removeRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "成员", name = "person") String person
    ) throws CircuitException;

    @CjOpenport(usage = "移除洇取人。只有洇取器的创建人才有权限")
    void removeRecipients2(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "成员", name = "person") String person,
            @CjOpenportParameter(usage = "激励代码，如\n" +
                    "like\n" +
                    "comment\n" +
                    "ingeo\n" +
                    "pull-in" +
                    "等等", name = "encourageCode") String encourageCode
    ) throws CircuitException;

    @CjOpenport(usage = "更新人数上限")
    void updateMaxRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "人数上限", name = "maxRecipients") long maxRecipients
            ) throws CircuitException;

    @CjOpenport(usage = "添加权重到指定的收取人，如果为负表示减权重，如果减少到负则权重置为0")
    void addWeightsToRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "成员", name = "person") String person,
            @CjOpenportParameter(usage = "激励代码，如\n" +
                    "like\n" +
                    "comment\n" +
                    "ingeo\n" +
                    "等等", name = "encourageCode") String encourageCode,
            @CjOpenportParameter(usage = "权重，正或负", name = "weights") BigDecimal weights
    ) throws CircuitException;

    @CjOpenport(usage = "更新权重到指定的收取人")
    void updateRecipientsWeights(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取人id", name = "recipientsId") String recipientsId,
            @CjOpenportParameter(usage = "权重，正或负", name = "weights") BigDecimal weights
    ) throws CircuitException;

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


    @CjOpenport(usage = "运行洇取器,仅行主有权限")
    void startAbsorber(ISecuritySession securitySession,
                       @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;

    @CjOpenport(usage = "分页洇取人，支持地理洇取器")
    List<Recipients> pageRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页简单洇取器的洇取人摘要信息")
    List<RecipientsSummary> pageSimpleRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "统计洇取器的人数，仅对简单洇取器有效")
    long countRecipients(ISecuritySession securitySession,
                         @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;
}
