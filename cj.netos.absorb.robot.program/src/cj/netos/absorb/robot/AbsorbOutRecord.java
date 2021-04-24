package cj.netos.absorb.robot;

import cj.netos.absorb.robot.util.RobotUtils;

/**
 * Table: absorb_out_record
 */
public class AbsorbOutRecord {
    static transient String _bankid = "c18baf5393884b5046759df2786d83f2";
    /**
     * Column: sn
     */
    private String sn;

    /**
     * Column: person
     * Remark: 用户
     */
    private String person;

    /**
     * Column: nick_name
     * Remark: 昵称
     */
    private String nickName;

    /**
     * Column: currency
     * Remark: 币种
     */
    private String currency;

    /**
     * Column: amount
     * Remark: 退还金额
     */
    private Long amount;

    /**
     * Column: state
     * Remark: 0为创建 1为完成
     */
    private Integer state;

    /**
     * Column: ctime
     */
    private String ctime;

    /**
     * Column: status
     * Remark: 处理状态
     */
    private Integer status;

    /**
     * Column: message
     * Remark: 处理消息
     */
    private String message;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public BankWithdrawResult convertResult() {
        BankWithdrawResult result = new BankWithdrawResult();
        result.setSn(sn);
        result.setBankid(_bankid);
        result.setCtime(this.ctime);
        result.setDtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        result.setNote(note);
        result.setPersonName(nickName);
        result.setReqAmount(amount);
        result.setRealAmount(amount);
        result.setState(1);
        result.setWithdrawer(person);
        result.setShunter("absorbs");
        return result;
    }
}