package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.bo.DomainBulletin;
import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.bo.QrcodeSliceTemplateBO;
import cj.netos.absorb.robot.bo.RecipientsSummary;
import cj.netos.absorb.robot.model.*;
import cj.netos.absorb.robot.result.AbsorberResult;
import cj.netos.absorb.robot.result.QrcodeSliceResult;
import cj.netos.absorb.robot.result.QrcodeSliceTemplateResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.AccessTokenIn;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.PKeyInRequest;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportAppSecurity;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    @CjOpenport(usage = "分页我创建的洇取器")
    List<AbsorberResult> pageMyAbsorber(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器类型。-1指所有洇取器；0指简单洇取器；2指地理洇取器；3余额洇取器", name = "type") int type,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页我创建的洇取器")
    List<AbsorberResult> pageMyAbsorberByUsage(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "用途：\n" +
                    "0网流管道\n" +
                    "1地理感知器\n" +
                    "2街道\n" +
                    "3金证喷泉\n" +
                    "4抢元宝", name = "usage") int usage,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页我参与的洇取器")
    List<AbsorberResult> pageJioninAbsorberByUsage(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "用途：\n" +
                    "0网流管道\n" +
                    "1地理感知器\n" +
                    "2街道\n" +
                    "3金证喷泉\n" +
                    "4抢元宝", name = "usage") int usage,
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

    @CjOpenport(usage = "添加自已为洇金收取人。仅限洇取器主人才有此权限")
    void addRecipients3(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
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

    @CjOpenport(usage = "移除洇取人自已")
    void removeRecipients3(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
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


    @CjOpenport(usage = "添加评论的权重到收取人")
    void addCommentWeightsOfRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "激励代码，如\n" +
                    "like\n" +
                    "comment\n" +
                    "ingeo\n" +
                    "等等", name = "encourageCode") String encourageCode
    ) throws CircuitException;

    @CjOpenport(usage = "减少评论权重到收取人。成功减除为true,否则为false,注：只管减，但小于等于1.5减法无效则返回false。")
    boolean subCommentWeightOfRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "激励代码，如\n" +
                    "like\n" +
                    "comment\n" +
                    "ingeo\n" +
                    "等等", name = "encourageCode") String encourageCode
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
    List<Recipients> pageGeoRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
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

    @CjOpenport(usage = "分页简单洇取器的洇取人摘要信息")
    List<Recipients> pageSimpleRecipientsOnlyMe(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页简单洇取器的洇取人摘要信息,含地理洇取人")
    List<Recipients> pageRecipientsOnlyMe(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "统计洇取器的人数，仅对简单洇取器有效")
    long countRecipients(ISecuritySession securitySession,
                         @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;

    @CjOpenport(usage = "配置码片模板，每次调用则覆盖，必须管理员权限", command = "post")
    void configQrcodeSliceTemplate(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "模板配置信息", name = "templates", elementType = QrcodeSliceTemplateBO.class, in = PKeyInRequest.content, simpleModelFile = "/qrcodeSliceTemplate.md") List<QrcodeSliceTemplateBO> templates
    ) throws CircuitException;

    @CjOpenport(usage = "创建码片，依据模板。如果需要修改属性请在其后调用更新属性的方法")
    List<QrcodeSliceResult> createQrcodeSlice(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "码片模板标识", name = "template") String template,
            @CjOpenportParameter(usage = "码片过期时间，0为永不过期", name = "expire") long expire,
            @CjOpenportParameter(usage = "发码的位置", name = "location") LatLng location,
            @CjOpenportParameter(usage = "半径，用于搜范围内的猫", name = "radius") long radius,
            @CjOpenportParameter(usage = "生成码片依据的猫（洇取器）的标识，如果有的话", name = "originAbsorber") String originAbsorber,
            @CjOpenportParameter(usage = "生成码片依据的公众，必有。不论猫是否为空", name = "originPerson") String originPerson,
            @CjOpenportParameter(usage = "要生成的本批码片数量", name = "count") int count,
            @CjOpenportParameter(usage = "自定义的模板属性，如果有", name = "props",elementType = {String.class,TemplateProp.class}) Map<String,TemplateProp> props,
            @CjOpenportParameter(usage = "备注，在码片上显示", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "更新码片属性")
    void updateQrcodeSliceProperty(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "码片标识", name = "slice") String slice,
            @CjOpenportParameter(usage = "码片的属性标识", name = "propId") String propId,
            @CjOpenportParameter(usage = "码片的属性值", name = "propValue") String propValue
    ) throws CircuitException;

    @CjOpenport(usage = "获取码片模板")
    QrcodeSliceTemplateResult getQrcodeSliceTemplate(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "码片模板标识", name = "id") String id
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取码片模板")
    List<QrcodeSliceTemplateResult> pageQrcodeSliceTemplate(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取码片批次")
    List<SliceBatch> pageQrcodeSliceBatch(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取当前用户创建的码片")
    List<QrcodeSliceResult> pageQrcodeSlice(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "获取码片")
    QrcodeSliceResult getQrcodeSlice(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "码片标识", name = "slice") String slice
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取当前用户创建的码片")
    List<QrcodeSliceResult> pageQrcodeSliceOfBatch(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "批次标识", name = "batchno") String batchno,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "添加余额洇取人，该洇取人此时仅为以码片标识占位，如果已占位则什么也不做")
    void addQrcodeSliceRecipients(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "码片标识", name = "qrcodeSlice") String qrcodeSlice
    ) throws CircuitException;

    @CjOpenport(usage = "判断是否不能为指定的涸取器发码")
    boolean canntPubSliceRecipients(ISecuritySession securitySession,
                                    @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid
    ) throws CircuitException;

    @CjOpenport(usage = "当前访问者消费码片")
    void consumeQrcodeSlice(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "码片标识", name = "qrcodeSlice") String qrcodeSlice
    ) throws CircuitException;

    @CjOpenportAppSecurity
    @CjOpenport(usage = "当前访问者消费码片", tokenIn = AccessTokenIn.nope)
    void consumeQrcodeSlice2(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "消费者，公号", name = "consumer") String consumer,
            @CjOpenportParameter(usage = "消费者昵称", name = "nickName") String nickName,
            @CjOpenportParameter(usage = "码片标识", name = "qrcodeSlice") String qrcodeSlice
    ) throws CircuitException;

    @CjOpenport(usage = "判断我是否可能创建新码片，如有未消费码片则不可创建")
    boolean cannotCreateQrocdeSlice(
            ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取我的未消费的码片列表")
    public List<QrcodeSlice> listUnconsumeSlices(
            ISecuritySession securitySession
    )throws CircuitException;
}
