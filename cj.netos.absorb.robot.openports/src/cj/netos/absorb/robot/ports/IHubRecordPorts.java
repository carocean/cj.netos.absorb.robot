package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.WithdrawRecord;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.math.BigDecimal;

@CjOpenports(usage = "洇取中心交易记录流水服务")
public interface IHubRecordPorts extends IOpenportService {
    @CjOpenport(usage = "获取洇取单")
    RecipientsRecord getRecipientsRecord(ISecuritySession securitySession,
                                         @CjOpenportParameter(usage = "订单号", name = "record_sn") String record_sn
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

    @CjOpenport(usage = "获取洇取器投资单")
    InvestRecord getInvestRecord(ISecuritySession securitySession,
                                 @CjOpenportParameter(usage = "订单号", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇取器向纹银银行提取洇金单")
    WithdrawRecord getWithdrawRecord(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "订单号", name = "record_sn") String record_sn
    ) throws CircuitException;
}
