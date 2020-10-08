package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: recipients_record
 */
public class RecipientsRecord {
    /**
     * Column: sn
     * Remark: 标识
     */
    private String sn;

    /**
     * Column: recipient
     * Remark: 收取人公号，与absorber一起对应recipients表的标识 一个收取人会多次领取发到同一个洇取器的洇金
     */
    private String recipient;

    /**
     * Column: amount
     * Remark: 派出的金额 单位为分，充许14位小数
     */
    private BigDecimal amount;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    /**
     * Column: refsn
     * Remark: 如果是从洇金桶派发的此处关联absorber_bill的sn
     */
    private String refsn;

    /**
     * Column: absorber
     */
    private String absorber;

    /**
     * Column: encourage_code
     * Remark: 对应recipients的相应字段
     */
    private String encourageCode;

    /**
     * Column: encourage_by
     * Remark: 被什么激励。如果激励代码是pubSlice此处为码片标识
     */
    private String encourageBy;

    /**
     * Column: encourage_cause
     * Remark: 对应recipients的相应字段
     */
    private String encourageCause;

    /**
     * Column: order
     * Remark: 类型 0 银行投资withdraw_record 1 投资者投资invest_record
     */
    private Integer order;

    /**
     * Column: recipients_id
     * Remark: 关联洇取人主键
     */
    private String recipientsId;

    /**
     * Column: year
     * Remark: 年
     */
    private Integer year;

    /**
     * Column: month
     * Remark: 月，比实际月-1
     */
    private Integer month;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public String getRefsn() {
        return refsn;
    }

    public void setRefsn(String refsn) {
        this.refsn = refsn == null ? null : refsn.trim();
    }

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber == null ? null : absorber.trim();
    }

    public String getEncourageCode() {
        return encourageCode;
    }

    public void setEncourageCode(String encourageCode) {
        this.encourageCode = encourageCode == null ? null : encourageCode.trim();
    }

    public String getEncourageBy() {
        return encourageBy;
    }

    public void setEncourageBy(String encourageBy) {
        this.encourageBy = encourageBy == null ? null : encourageBy.trim();
    }

    public String getEncourageCause() {
        return encourageCause;
    }

    public void setEncourageCause(String encourageCause) {
        this.encourageCause = encourageCause == null ? null : encourageCause.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getRecipientsId() {
        return recipientsId;
    }

    public void setRecipientsId(String recipientsId) {
        this.recipientsId = recipientsId == null ? null : recipientsId.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}