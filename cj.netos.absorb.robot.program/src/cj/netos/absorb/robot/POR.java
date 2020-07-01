package cj.netos.absorb.robot;

import java.math.BigDecimal;

public class POR{
    GeoReceptorBO receptor;
    BigDecimal distance;

    public GeoReceptorBO getReceptor() {
        return receptor;
    }

    public void setReceptor(GeoReceptorBO receptor) {
        this.receptor = receptor;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}