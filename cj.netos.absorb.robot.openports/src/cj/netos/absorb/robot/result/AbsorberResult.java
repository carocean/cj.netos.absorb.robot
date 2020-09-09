package cj.netos.absorb.robot.result;

import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.AbsorberBucket;

public class AbsorberResult {
    Absorber absorber;
    AbsorberBucket bucket;

    public AbsorberResult() {
    }

    public AbsorberResult(Absorber absorber, AbsorberBucket bucket) {
        this.absorber = absorber;
        this.bucket = bucket;
    }

    public Absorber getAbsorber() {
        return absorber;
    }

    public void setAbsorber(Absorber absorber) {
        this.absorber = absorber;
    }

    public AbsorberBucket getBucket() {
        return bucket;
    }

    public void setBucket(AbsorberBucket bucket) {
        this.bucket = bucket;
    }
}
