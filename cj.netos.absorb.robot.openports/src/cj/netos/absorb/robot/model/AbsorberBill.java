package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: absorber_bill
 */
public class AbsorberBill {
    /**
     * Column: sn
     */
    private String sn;

    /**
     * Column: person
     * Remark: 相应的提现人（从银行提取账金的人）或投资人
     */
    private String person;

    /**
     * Column: refsn
     * Remark: 关联提现单或投资单
     */
    private String refsn;

    /**
     * Column: amount
     * Remark: 金额
     */
    private BigDecimal amount;

    /**
     * Column: w_balance
     * Remark: 银行投资余额
     */
    private BigDecimal wBalance;

    /**
     * Column: p_balance
     * Remark: 公众投资余额
     */
    private BigDecimal pBalance;

    /**
     * Column: after_price
     * Remark: 余额，对应absorber_bucket中的提现投资或公众投资总计
     */
    private BigDecimal afterPrice;

    /**
     * Column: order
     * Remark: 0 银行投资withdraw_record 1 投资者投资invest_record 
     */
    private Integer order;

    /**
     * Column: absorber
     * Remark: 洇取器标识
     */
    private String absorber;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getwBalance() {
        return wBalance;
    }

    public void setwBalance(BigDecimal wBalance) {
        this.wBalance = wBalance;
    }

    public BigDecimal getpBalance() {
        return pBalance;
    }

    public void setpBalance(BigDecimal pBalance) {
        this.pBalance = pBalance;
    }

    public BigDecimal getAfterPrice() {
        return afterPrice;
    }

    public void setAfterPrice(BigDecimal afterPrice) {
        this.afterPrice = afterPrice;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber == null ? null : absorber.trim();
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
}