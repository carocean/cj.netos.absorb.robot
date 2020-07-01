package cj.netos.absorb.robot.model;

/**
 * Table: invest_record
 */
public class InvestRecord {
    /**
     * Column: sn
     * Remark: 序号
     */
    private String sn;

    /**
     * Column: absorber
     * Remark: 洇取器标识
     */
    private String absorber;

    /**
     * Column: amount
     * Remark: 投资金额
     */
    private Long amount;

    /**
     * Column: invester
     * Remark: 投资人
     */
    private String invester;

    /**
     * Column: person_name
     * Remark: 投资人中文名
     */
    private String personName;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    /**
     * Column: note
     */
    private String note;

    /**
     * Column: out_trade_sn
     * Remark: 关联钱包中心支付订单号
     */
    private String outTradeSn;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getInvester() {
        return invester;
    }

    public void setInvester(String invester) {
        this.invester = invester == null ? null : invester.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
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

    public String getOutTradeSn() {
        return outTradeSn;
    }

    public void setOutTradeSn(String outTradeSn) {
        this.outTradeSn = outTradeSn == null ? null : outTradeSn.trim();
    }
}