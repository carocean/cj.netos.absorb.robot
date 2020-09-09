package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: domain_bucket
 */
public class DomainBucket {
    /**
     * Column: bank
     * Remark: 纹银银行标识
     */
    private String bank;

    /**
     * Column: waa_price
     * Remark: 加权平均价格。来自于absorber_bucket的price的加权平均
     */
    private BigDecimal waaPrice;

    /**
     * Column: utime
     * Remark: 更新时间
     */
    private String utime;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public BigDecimal getWaaPrice() {
        return waaPrice;
    }

    public void setWaaPrice(BigDecimal waaPrice) {
        this.waaPrice = waaPrice;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime == null ? null : utime.trim();
    }
}