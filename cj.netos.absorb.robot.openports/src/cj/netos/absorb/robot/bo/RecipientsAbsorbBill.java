package cj.netos.absorb.robot.bo;

import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.AbsorberBucket;
import cj.netos.absorb.robot.model.Recipients;

import java.math.BigDecimal;

public class RecipientsAbsorbBill {
   String absorber;
   Recipients recipients;
   BigDecimal amount;
   String refsn;//派发记录流水号

    public RecipientsAbsorbBill() {
    }

    public RecipientsAbsorbBill(String absorber, Recipients recipients, BigDecimal amount, String refsn) {
        this.absorber = absorber;
        this.recipients = recipients;
        this.amount = amount;
        this.refsn = refsn;
    }

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber;
    }

    public Recipients getRecipients() {
        return recipients;
    }

    public void setRecipients(Recipients recipients) {
        this.recipients = recipients;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRefsn() {
        return refsn;
    }

    public void setRefsn(String refsn) {
        this.refsn = refsn;
    }
}
