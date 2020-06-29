package cj.netos.absorb.robot.model;

/**
 * Table: absorber_bill
 */
public class AbsorberBill {
    /**
     * Column: sn
     */
    private String sn;

    /**
     * Column: title
     * Remark: 显示名，一般是人中文名
     */
    private String title;

    /**
     * Column: participant
     * Remark: 参与者：投资者和收件人
     */
    private String participant;

    /**
     * Column: absorber
     * Remark: 归属的洇取器
     */
    private String absorber;

    /**
     * Column: order
     * Remark: 指令 0为投资 1为提取
     */
    private Integer order;

    /**
     * Column: amount
     * Remark: 在本洇取器上的金额
     */
    private Long amount;

    /**
     * Column: balance
     * Remark: 余额
     */
    private Long balance;

    /**
     * Column: refsn
     * Remark: 仅在order=0时关联的投资单标识，指投资者从零钱转入的转入单的标识 对于收件人流水，采用收件人流水的外键与之关联，因为一次提取的账单会被派发生成多个收件人流水 
     */
    private String refsn;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    /**
     * Column: note
     * Remark: 备注
     */
    private String note;

    /**
     * Column: workday
     * Remark: 会计日期
     */
    private String workday;

    /**
     * Column: day
     * Remark: 天，1-31'
     */
    private Integer day;

    /**
     * Column: month
     * Remark: 月，0-11 
     */
    private Integer month;

    /**
     * Column: weekday
     * Remark: 周 1-7
     */
    private Integer weekday;

    /**
     * Column: season
     * Remark: 季 0-3
     */
    private Integer season;

    /**
     * Column: year
     * Remark: 年
     */
    private Integer year;

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

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant == null ? null : participant.trim();
    }

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber == null ? null : absorber.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getRefsn() {
        return refsn;
    }

    public void setRefsn(String refsn) {
        this.refsn = refsn == null ? null : refsn.trim();
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

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
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