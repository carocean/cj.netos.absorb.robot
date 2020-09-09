package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: absorber_bucket
 */
public class AbsorberBucket {
    /**
     * Column: absorber
     * Remark: 洇取器
     */
    private String absorber;

    /**
     * Column: bank
     */
    private String bank;

    /**
     * Column: w_invest_amount
     * Remark: 银行投资总计
     */
    private BigDecimal wInvestAmount;

    /**
     * Column: p_invest_amount
     * Remark: 个人投资总计
     */
    private BigDecimal pInvestAmount;

    /**
     * Column: price
     * Remark: 洇取器价格，即，个人投资总和/银行投资总额,也就是上面两字段相除
     */
    private BigDecimal price;

    /**
     * Column: times
     * Remark: 洇取次数
     */
    private Long times;

    /**
     * Column: utime
     */
    private String utime;

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber == null ? null : absorber.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public BigDecimal getwInvestAmount() {
        return wInvestAmount;
    }

    public void setwInvestAmount(BigDecimal wInvestAmount) {
        this.wInvestAmount = wInvestAmount;
    }

    public BigDecimal getpInvestAmount() {
        return pInvestAmount;
    }

    public void setpInvestAmount(BigDecimal pInvestAmount) {
        this.pInvestAmount = pInvestAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime == null ? null : utime.trim();
    }
}