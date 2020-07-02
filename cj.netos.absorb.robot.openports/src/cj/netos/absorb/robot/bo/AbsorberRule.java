package cj.netos.absorb.robot.bo;

import java.math.BigDecimal;
import java.util.Map;

public class AbsorberRule {
    String category;
    BigDecimal weight;
    long exitExpire;
    long exitAmount;
    long exitTimes;
    long maxRecipients;
    Map<String,BigDecimal> encourage;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public long getExitExpire() {
        return exitExpire;
    }

    public void setExitExpire(long exitExpire) {
        this.exitExpire = exitExpire;
    }

    public long getExitAmount() {
        return exitAmount;
    }

    public void setExitAmount(long exitAmount) {
        this.exitAmount = exitAmount;
    }

    public long getExitTimes() {
        return exitTimes;
    }

    public void setExitTimes(long exitTimes) {
        this.exitTimes = exitTimes;
    }

    public long getMaxRecipients() {
        return maxRecipients;
    }

    public void setMaxRecipients(long maxRecipients) {
        this.maxRecipients = maxRecipients;
    }

    public Map<String, BigDecimal> getEncourage() {
        return encourage;
    }

    public void setEncourage(Map<String, BigDecimal> encourage) {
        this.encourage = encourage;
    }
}
