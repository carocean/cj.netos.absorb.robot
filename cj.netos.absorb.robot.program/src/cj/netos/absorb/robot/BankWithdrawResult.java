package cj.netos.absorb.robot;

public class BankWithdrawResult {
    String sn;
    String withdrawer;
    String personName;
    long reqAmount;
    long realAmount;
    int state;
    String bankid;
    String ctime;
    String dtime;
    String note;
    String shunter;
    String outTradeSn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getWithdrawer() {
        return withdrawer;
    }

    public void setWithdrawer(String withdrawer) {
        this.withdrawer = withdrawer;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getReqAmount() {
        return reqAmount;
    }

    public void setReqAmount(long reqAmount) {
        this.reqAmount = reqAmount;
    }

    public long getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(long realAmount) {
        this.realAmount = realAmount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getShunter() {
        return shunter;
    }

    public void setShunter(String shunter) {
        this.shunter = shunter;
    }

    public String getOutTradeSn() {
        return outTradeSn;
    }

    public void setOutTradeSn(String outTradeSn) {
        this.outTradeSn = outTradeSn;
    }
}
