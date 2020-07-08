package cj.netos.absorb.robot.bo;

import java.math.BigDecimal;

public class RecipientsSummary {
    String absorber;
    String person;
    String personName;
    String ctime;
    BigDecimal weights;
    BigDecimal amount;
    String encourageCauses;

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public BigDecimal getWeights() {
        return weights;
    }

    public void setWeights(BigDecimal weights) {
        this.weights = weights;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEncourageCauses() {
        return encourageCauses;
    }

    public void setEncourageCauses(String encourageCauses) {
        this.encourageCauses = encourageCauses;
    }
}
