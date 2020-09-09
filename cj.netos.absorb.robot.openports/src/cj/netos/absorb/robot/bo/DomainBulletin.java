package cj.netos.absorb.robot.bo;

import cj.netos.absorb.robot.model.DomainBucket;

import java.math.BigDecimal;

public class DomainBulletin {
    DomainBucket bucket;
    BigDecimal absorbWeights;
    long absorbCount;

    public long getAbsorbCount() {
        return absorbCount;
    }

    public void setAbsorbCount(long absorbCount) {
        this.absorbCount = absorbCount;
    }

    public DomainBucket getBucket() {
        return bucket;
    }

    public void setBucket(DomainBucket bucket) {
        this.bucket = bucket;
    }

    public BigDecimal getAbsorbWeights() {
        return absorbWeights;
    }

    public void setAbsorbWeights(BigDecimal absorbWeights) {
        this.absorbWeights = absorbWeights;
    }
}
