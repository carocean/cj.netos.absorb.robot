package cj.netos.absorb.robot.model;

/**
 * Table: absorber_bucket
 */
public class AbsorberBucket {
    /**
     * Column: id
     * Remark: 标识
     */
    private String id;

    /**
     * Column: amount
     * Remark: 洇取器余额，如果大于0则派发程序取出并派发 个人或商家可以从钱包的零钱转钱到洇取器供派发
     */
    private Long amount;

    /**
     * Column: period
     * Remark: 派发时间间隔
     */
    private Long period;

    /**
     * Column: per_amount
     * Remark: 每次派发金额
     */
    private Long perAmount;

    /**
     * Column: absorber
     * Remark: 归属的洇取器，一个洇取器一个桶
     */
    private String absorber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public Long getPerAmount() {
        return perAmount;
    }

    public void setPerAmount(Long perAmount) {
        this.perAmount = perAmount;
    }

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber == null ? null : absorber.trim();
    }
}