package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: tail_bill
 */
public class TailBill {
    /**
     * Column: sn
     */
    private String sn;

    /**
     * Column: refsn
     * Remark: 关联原始派发单号，或是提现单，或是投单
     */
    private String refsn;

    /**
     * Column: amount
     * Remark: 金额，正为存，负为取
     */
    private BigDecimal amount;

    /**
     * Column: order
     * Remark: 0为withdraw_record存入 1为absorber_bill存入 2为取出
     */
    private Integer order;

    /**
     * Column: bankid
     * Remark: 关联自哪个行
     */
    private String bankid;

    /**
     * Column: balance
     */
    private BigDecimal balance;

    /**
     * Column: ctime
     */
    private String ctime;

    /**
     * Column: note
     */
    private String note;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getRefsn() {
        return refsn;
    }

    public void setRefsn(String refsn) {
        this.refsn = refsn == null ? null : refsn.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
}