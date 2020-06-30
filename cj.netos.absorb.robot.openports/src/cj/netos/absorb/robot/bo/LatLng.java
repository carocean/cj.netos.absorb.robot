package cj.netos.absorb.robot.bo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LatLng {
    BigDecimal latitude;
    BigDecimal longitude;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }


    public String toJson() {
        return String.format("{\"latitude\":%s,\"longitude\":%s}", latitude, longitude);
    }
}
