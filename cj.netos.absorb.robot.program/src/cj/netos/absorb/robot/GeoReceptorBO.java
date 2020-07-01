package cj.netos.absorb.robot;

import cj.netos.absorb.robot.bo.LatLng;

import java.math.BigDecimal;


public class GeoReceptorBO {
    String id;
    String title;
    String category;
    String creator;
    LatLng location;
    BigDecimal radius;
    BigDecimal uDistance;
    long ctime;
    String device;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }

    public BigDecimal getuDistance() {
        return uDistance;
    }

    public void setuDistance(BigDecimal uDistance) {
        this.uDistance = uDistance;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
