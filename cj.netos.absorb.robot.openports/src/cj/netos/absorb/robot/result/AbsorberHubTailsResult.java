package cj.netos.absorb.robot.result;

import java.math.BigDecimal;

/**
 * Table: tail_bill
 */
public class AbsorberHubTailsResult {
    /**
     * Column: sn
     */
    private String sn;

    /**
     * Column: person
     * Remark: 操作人
     */
    private String person;
    private String personName;
    /**
     * Column: refsn
     * Remark: 关联原始派发单号，或是提现单，或是投单
     */
    private String refsn;

    /**
     * Column: amount
     * Remark: 金额，正为存，负为取
     */
    private long amount;

    /**
     * Column: order
     * Remark: 0为withdraw_record存入 1为invest_record存入 2为取出
     */
    private Integer order;

    /**
     * Column: bankid
     * Remark: 关联自哪个行
     */
    private String bankid;

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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getRefsn() {
        return refsn;
    }

    public void setRefsn(String refsn) {
        this.refsn = refsn == null ? null : refsn.trim();
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}