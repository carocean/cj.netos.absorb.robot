package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: recipients_balance_bill
 */
public class RecipientsBalanceBill {
    /**
     * Column: sn
     * Remark: 标识
     */
    private String sn;

    /**
     * Column: title
     * Remark: 标题
     */
    private String title;

    /**
     * Column: person
     * Remark: 转取人
     */
    private String person;

    /**
     * Column: person_name
     * Remark: 姓名
     */
    private String personName;

    /**
     * Column: recipients_id
     * Remark: 洇取人标识
     */
    private String recipientsId;

    /**
     * Column: qrcode_slice
     * Remark: 码片标识
     */
    private String qrcodeSlice;

    /**
     * Column: amount
     * Remark: 金额
     */
    private BigDecimal amount;

    /**
     * Column: balance
     */
    private BigDecimal balance;

    /**
     * Column: order
     * Remark: 0为增加洇金到余额是入账，由refsn关联到recipients_record表 1为余额转结到钱包
     */
    private Integer order;

    /**
     * Column: ref_sn
     * Remark: 当存入洇金到余额时关联到recipients_record表
     */
    private String refSn;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getRecipientsId() {
        return recipientsId;
    }

    public void setRecipientsId(String recipientsId) {
        this.recipientsId = recipientsId == null ? null : recipientsId.trim();
    }

    public String getQrcodeSlice() {
        return qrcodeSlice;
    }

    public void setQrcodeSlice(String qrcodeSlice) {
        this.qrcodeSlice = qrcodeSlice == null ? null : qrcodeSlice.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getRefSn() {
        return refSn;
    }

    public void setRefSn(String refSn) {
        this.refSn = refSn == null ? null : refSn.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }
}