package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: domain_bill
 */
public class DomainBill {
    /**
     * Column: sn
     * Remark: 序列号
     */
    private String sn;

    /**
     * Column: absorber
     * Remark: 洇取器标识
     */
    private String absorber;

    /**
     * Column: refsn
     * Remark: 关联absorber_bill的sn
     */
    private String refsn;

    /**
     * Column: waa_price
     * Remark: 价格来自于abosrber_bucket的price字段的加权平均，直接可使用sql语句来计算即可
     */
    private BigDecimal waaPrice;

    /**
     * Column: after_waa_price
     * Remark: 变化后的价格
     */
    private BigDecimal afterWaaPrice;

    /**
     * Column: absorb_count
     * Remark: 本次统计的符合条件(大于等于加权平均价)的洇取器数
     */
    private Long absorbCount;

    /**
     * Column: w_invest_amount
     * Remark: 本次统计的各符合条件的洇取器被提现者投资的总金额
     */
    private BigDecimal wInvestAmount;

    /**
     * Column: p_invest_amount
     * Remark: 本次统计的各符合条件的洇取器被公众投资的总金额
     */
    private BigDecimal pInvestAmount;

    /**
     * Column: order
     * Remark: 0 银行投资withdraw_record 1 投资者投资invest_record 
     */
    private Integer order;

    /**
     * Column: ctime
     */
    private String ctime;

    /**
     * Column: note
     */
    private String note;

    /**
     * Column: workday
     */
    private String workday;

    /**
     * Column: day
     */
    private Integer day;

    /**
     * Column: month
     */
    private Integer month;

    /**
     * Column: season
     */
    private Integer season;

    /**
     * Column: year
     */
    private Integer year;

    /**
     * Column: bankid
     * Remark: 纹银银行标识
     */
    private String bankid;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber == null ? null : absorber.trim();
    }

    public String getRefsn() {
        return refsn;
    }

    public void setRefsn(String refsn) {
        this.refsn = refsn == null ? null : refsn.trim();
    }

    public BigDecimal getWaaPrice() {
        return waaPrice;
    }

    public void setWaaPrice(BigDecimal waaPrice) {
        this.waaPrice = waaPrice;
    }

    public BigDecimal getAfterWaaPrice() {
        return afterWaaPrice;
    }

    public void setAfterWaaPrice(BigDecimal afterWaaPrice) {
        this.afterWaaPrice = afterWaaPrice;
    }

    public Long getAbsorbCount() {
        return absorbCount;
    }

    public void setAbsorbCount(Long absorbCount) {
        this.absorbCount = absorbCount;
    }

    public BigDecimal getwInvestAmount() {
        return wInvestAmount;
    }

    public void setwInvestAmount(BigDecimal wInvestAmount) {
        this.wInvestAmount = wInvestAmount;
    }

    public BigDecimal getpInvestAmount() {
        return pInvestAmount;
    }

    public void setpInvestAmount(BigDecimal pInvestAmount) {
        this.pInvestAmount = pInvestAmount;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getWorkday() {
        return workday;
    }

    public void setWorkday(String workday) {
        this.workday = workday == null ? null : workday.trim();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }
}