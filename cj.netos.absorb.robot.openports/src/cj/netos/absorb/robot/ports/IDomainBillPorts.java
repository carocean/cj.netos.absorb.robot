package cj.netos.absorb.robot.ports;

import cj.netos.absorb.robot.model.DomainBill;
import cj.netos.absorb.robot.model.HubTails;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.List;

@CjOpenports(usage = "域进出账单")
public interface IDomainBillPorts extends IOpenportService {
    @CjOpenport(usage = "分页账单")
    List<DomainBill> pageBill(ISecuritySession securitySession,
                              @CjOpenportParameter(usage = "纹银银行号", name = "wenyBankID") String wenyBankID,
                              @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                              @CjOpenportParameter(usage = "当前记录位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "获取指定月份账单")
    List<DomainBill> getBillOfMonth(ISecuritySession securitySession,
                                  @CjOpenportParameter(usage = "纹银银行号", name = "wenyBankID") String wenyBankID,
                                  @CjOpenportParameter(usage = "年份", name = "year") int year,
                                  @CjOpenportParameter(usage = "月份。（java特性，实际用份减1）", name = "month") int month,
                                  @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                  @CjOpenportParameter(usage = "当前记录位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "获取指定月份账单")
    List<DomainBill> pageBillOfMonth(ISecuritySession securitySession,
                                   @CjOpenportParameter(usage = "纹银银行号", name = "wenyBankID") String wenyBankID,
                                   @CjOpenportParameter(usage = "指令：0为withdraw_record存入\n" +
                                           "1为invest_record存入\n" +
                                           "2为取出", name = "order") int order,
                                   @CjOpenportParameter(usage = "年份", name = "year") int year,
                                   @CjOpenportParameter(usage = "月份。（java特性，实际用份减1）", name = "month") int month,
                                   @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                   @CjOpenportParameter(usage = "当前记录位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "获取指定月份账单总金额")
    String totalBillOfMonth(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "纹银银行号", name = "wenyBankID") String wenyBankID,
            @CjOpenportParameter(usage = "年份", name = "year") int year,
            @CjOpenportParameter(usage = "月份。（java特性，实际用份减1）", name = "month") int month
    ) throws CircuitException;

}
