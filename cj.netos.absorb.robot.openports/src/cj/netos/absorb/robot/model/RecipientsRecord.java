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
}