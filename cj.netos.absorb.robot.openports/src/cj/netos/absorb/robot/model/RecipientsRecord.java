package cj.netos.absorb.robot.model;

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
     * Column: absorber
     * Remark: 洇取器
     */
    private String absorber;

    /**
     * Column: recipient
     * Remark: 收件人
     */
    private String recipient;

    /**
     * Column: person_name
     * Remark: 收件人姓名
     */
    private String personName;

    /**
     * Column: amount
     * Remark: 派出的金额
     */
    private Long amount;

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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
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
}