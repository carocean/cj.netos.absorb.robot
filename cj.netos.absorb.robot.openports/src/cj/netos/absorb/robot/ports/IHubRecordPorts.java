package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.WithdrawRecord;
import cj.netos.absorb.robot.result.RecipientsRecordInfoResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.math.BigDecimal;
import java.util.List;

@CjOpenports(usage = "洇取中心交易记录流水服务")
public interface IHubRecordPorts extends IOpenportService {
    @CjOpenport(usage = "获取洇取单")
    RecipientsRecord getRecipientsRecord(ISecuritySession securitySession,
                                         @CjOpenportParameter(usage = "订单号", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇取单信息")
    RecipientsRecordInfoResult getRecipientsRecordInfo(ISecuritySession securitySession,
                                                       @CjOpenportParameter(usage = "订单号", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取洇取单")
    List<RecipientsRecord> pageRecipientsRecordByPerson(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
            @CjOpenportParameter(usage = "洇取人", name = "recipients") String recipients,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取洇取单")
    List<RecipientsRecord> pageRecipientsRecordById(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取洇取单")
    List<RecipientsRecord> pageRecipientsRecordWhere(ISecuritySession securitySession,
                                                     @CjOpenportParameter(usage = "洇取器标识", name = "absorber") String absorber,
                                                     @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId,
                                                     @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                     @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取洇取单")
    List<RecipientsRecord> pageRecipientsRecordWhere3(ISecuritySession securitySession,
                                                      @CjOpenportParameter(usage = "洇取器标识", name = "absorber") String absorber,
                                                      @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId,
                                                      @CjOpenportParameter(usage = "年", name = "year") int year,
                                                      @CjOpenportParameter(usage = "月，为实际月-1", name = "month") int month,
                                                      @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                      @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取洇取单")
    List<RecipientsRecord> pageRecipientsRecordByOrderWhere(ISecuritySession securitySession,
                                                            @CjOpenportParameter(usage = "洇取器标识", name = "absorber") String absorber,
                                                            @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId,
                                                            @CjOpenportParameter(usage = "类型\n" +
                                                                    "0 银行投资withdraw_record\n" +
                                                                    "1 投资者投资invest_record", name = "order") int order,
                                                            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取洇取单")
    List<RecipientsRecord> pageRecipientsRecordByOrderWhere2(ISecuritySession securitySession,
                                                             @CjOpenportParameter(usage = "洇取器标识", name = "absorber") String absorber,
                                                             @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId,
                                                             @CjOpenportParameter(usage = "类型\n" +
                                                                     "0 银行投资withdraw_record\n" +
                                                                     "1 投资者投资invest_record", name = "order") int order,
                                                             @CjOpenportParameter(usage = "年", name = "year") int year,
                                                             @CjOpenportParameter(usage = "月，为实际月-1", name = "month") int month,
                                                             @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                             @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "统计一个洇取人得到的总金")
    BigDecimal totalRecipientsRecord(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "洇取器标识", name = "absorber") String absorber,
                                     @CjOpenportParameter(usage = "洇取人", name = "recipients") String recipients
    ) throws CircuitException;

    @CjOpenport(usage = "统计一个洇取人得到的总金")
    BigDecimal totalRecipientsRecordById(ISecuritySession securitySession,
                                         @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId
    ) throws CircuitException;

    @CjOpenport(usage = "统计一个洇取人得到的总金")
    BigDecimal totalRecipientsRecordWhere(ISecuritySession securitySession,
                                          @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
                                          @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId
    ) throws CircuitException;

    @CjOpenport(usage = "统计一个洇取人得到的总金")
    BigDecimal totalRecipientsRecordByOrderWhere(ISecuritySession securitySession,
                                                 @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
                                                 @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId,
                                                 @CjOpenportParameter(usage = "类型\n" +
                                                         "0 银行投资withdraw_record\n" +
                                                         "1 投资者投资invest_record", name = "order") int order
    ) throws CircuitException;

    @CjOpenport(usage = "统计一个洇取人得到的总金")
    BigDecimal totalRecipientsRecordByOrderWhere2(ISecuritySession securitySession,
                                                  @CjOpenportParameter(usage = "洇取器标识", name = "absorberid") String absorberid,
                                                  @CjOpenportParameter(usage = "洇取人标识", name = "recipientsId") String recipientsId,
                                                  @CjOpenportParameter(usage = "类型\n" +
                                                          "0 银行投资withdraw_record\n" +
                                                          "1 投资者投资invest_record", name = "order") int order,
                                                  @CjOpenportParameter(usage = "年", name = "year") int year,
                                                  @CjOpenportParameter(usage = "月，为实际月-1", name = "month") int month
    ) throws CircuitException;


    @CjOpenport(usage = "获取洇取器投资单")
    InvestRecord getInvestRecord(ISecuritySession securitySession,
                                 @CjOpenportParameter(usage = "订单号", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇取器向纹银银行提取洇金单")
    WithdrawRecord getWithdrawRecord(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "订单号", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "分页投单流水")
    List<InvestRecord> pageInvestRecord(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "洇取器标识", name = "absorber") String absorber,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "统计洇取器的总投单额")
    long totalAmountInvests(ISecuritySession securitySession,
                            @CjOpenportParameter(usage = "洇取器标识", name = "absorber") String absorber
    ) throws CircuitException;

    @CjOpenport(usage = "分页提现单流水")
    List<WithdrawRecord> pageWithdrawRecord(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid,
            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
            @CjOpenportParameter(usage = "页偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "统计提现单总额")
    long totalAmountWithdraws(ISecuritySession securitySession,
                              @CjOpenportParameter(usage = "银行标识", name = "bankid") String bankid
    ) throws CircuitException;
}
