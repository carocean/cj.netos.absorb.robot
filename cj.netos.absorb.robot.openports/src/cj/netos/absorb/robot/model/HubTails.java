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
}