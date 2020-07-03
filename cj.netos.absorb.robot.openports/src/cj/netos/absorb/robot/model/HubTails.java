package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: hub_tails
 */
public class HubTails {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: tail_admount
     * Remark: 每次派发的尾金
     */
    private BigDecimal tailAdmount;

    /**
     * Column: bankid
     * Remark: 哪个银行产生的
     */
    private String bankid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getTailAdmount() {
        return tailAdmount;
    }

    public void setTailAdmount(BigDecimal tailAdmount) {
        this.tailAdmount = tailAdmount;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }
}