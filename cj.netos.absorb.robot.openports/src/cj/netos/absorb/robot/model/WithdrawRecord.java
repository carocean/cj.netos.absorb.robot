package cj.netos.absorb.robot.model;

/**
 * Table: withdraw_record
 */
public class WithdrawRecord {
    /**
     * Column: sn
     * Remark: 序列号
     */
    private String sn;

    /**
     * Column: bankid
     * Remark: 银行标识
     */
    private String bankid;

    /**
     * Column: shunter
     * Remark: 分账器，固定为"absorbs"
     */
    private String shunter;

    /**
     * Column: alias
     * Remark: 分账器名，固定为洇金账户
     */
    private String alias;

    /**
     * Column: withdrawer
     * Remark: 提现者，固定为absorbRobot@system.netos
     */
    private String withdrawer;

    /**
     * Column: person_name
     * Remark: 提现者名，固定为：洇金提取器
     */
    private String personName;

    /**
     * Column: req_amount
     * Remark: 请求金额，响应的账金，并以此发起提现请求
     */
    private Long reqAmount;

    /**
     * Column: real_amount
     * Remark: 实际提得的金额
     */
    private Long realAmount;

    /**
     * Column: ctime
     * Remark: 创建记录的时间
     */
    private String ctime;

    /**
     * Column: cbtime
     * Remark: 调用纹银银行后的时间
     */
    private String cbtime;

    /**
     * Column: state
     * Remark: 提现单是否完成 0为提现中 1为完成（银行返回）
     */
    private Integer state;

    /**
     * Column: refsn
     * Remark: 关联纹银银行提现单号
     */
    private String refsn;

    /**
     * Column: status
     * Remark: 纹银银行返回状态码
     */
    private String status;

    /**
     * Column: message
     * Remark: 纹银银行返回状态信息
     */
    private String message;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public String getShunter() {
        return shunter;
    }

    public void setShunter(String shunter) {
        this.shunter = shunter == null ? null : shunter.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getWithdrawer() {
        return withdrawer;
    }

    public void setWithdrawer(String withdrawer) {
        this.withdrawer = withdrawer == null ? null : withdrawer.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public Long getReqAmount() {
        return reqAmount;
    }

    public void setReqAmount(Long reqAmount) {
        this.reqAmount = reqAmount;
    }

    public Long getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Long realAmount) {
        this.realAmount = realAmount;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public String getCbtime() {
        return cbtime;
    }

    public void setCbtime(String cbtime) {
        this.cbtime = cbtime == null ? null : cbtime.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRefsn() {
        return refsn;
    }

    public void setRefsn(String refsn) {
        this.refsn = refsn == null ? null : refsn.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}