package cj.netos.absorb.robot.bo;

import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.AbsorberBucket;
import cj.netos.absorb.robot.model.Recipients;

import java.math.BigDecimal;

public class RecipientsAbsorbBill {
    String absorberTitle;
    Recipients recipients;
    BigDecimal amount;
    String refsn;//派发记录流水号
    String refType;//引用的sn类型，recipientsRecord；recipientsBalanceBill

    public RecipientsAbsorbBill() {
    }

    public RecipientsAbsorbBill(String absorberTitle, Recipients recipients, BigDecimal amount, String refType, String refsn) {
        this.absorberTitle = absorberTitle;
        this.recipients = recipients;
        this.amount = amount;
        this.refType = refType;
        this.refsn = refsn;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getAbsorberTitle() {
        return absorberTitle;
    }

    public void setAbsorberTitle(String absorberTitle) {
        this.absorberTitle = absorberTitle;
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
