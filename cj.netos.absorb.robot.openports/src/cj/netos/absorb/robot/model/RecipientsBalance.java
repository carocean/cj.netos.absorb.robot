package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

/**
 * Table: recipients_balance
 */
public class RecipientsBalance {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: recipients
     * Remark: 洇取人标识
     */
    private String recipients;

    /**
     * Column: qrcode_slice
     * Remark: 码片标识
     */
    private String qrcodeSlice;

    /**
     * Column: amount
     * Remark: 余额
     */
    private BigDecimal amount;

    /**
     * Column: state
     * Remark: 0为继续接受洇金到余额 1为停止接受
     */
    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients == null ? null : recipients.trim();
    }

    public String getQrcodeSlice() {
        return qrcodeSlice;
    }

    public void setQrcodeSlice(String qrcodeSlice) {
        this.qrcodeSlice = qrcodeSlice == null ? null : qrcodeSlice.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}