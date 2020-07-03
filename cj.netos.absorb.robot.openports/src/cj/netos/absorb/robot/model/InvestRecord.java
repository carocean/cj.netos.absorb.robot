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
     * Column: out_trade_sn
     * Remark: 关联钱包中心支付订单号
     */
    private String outTradeSn;

    /**
     * Column: invest_order_no
     * Remark: 关联支付目标订单号，对洇取程序用处不大，但记录起来将来或可用
     */
    private String investOrderNo;

    /**
     * Column: invest_order_title
     * Remark: 关联支付目标订单标题
     */
    private String investOrderTitle;

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

    public String getOutTradeSn() {
        return outTradeSn;
    }

    public void setOutTradeSn(String outTradeSn) {
        this.outTradeSn = outTradeSn == null ? null : outTradeSn.trim();
    }

    public String getInvestOrderNo() {
        return investOrderNo;
    }

    public void setInvestOrderNo(String investOrderNo) {
        this.investOrderNo = investOrderNo == null ? null : investOrderNo.trim();
    }

    public String getInvestOrderTitle() {
        return investOrderTitle;
    }

    public void setInvestOrderTitle(String investOrderTitle) {
        this.investOrderTitle = investOrderTitle == null ? null : investOrderTitle.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}