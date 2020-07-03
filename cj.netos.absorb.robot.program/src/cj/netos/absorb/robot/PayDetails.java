package cj.netos.absorb.robot;

/**
 * Table: pay_details
 */
public class PayDetails {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: payee_code
     * Remark: 收款人代码
     */
    private String payeeCode;

    /**
     * Column: payee_name
     * Remark: 收款人名称
     */
    private String payeeName;

    /**
     * Column: payee_type
     * Remark: 收款人类型，如：商户、洇取器等非系统内用户
     */
    private String payeeType;

    /**
     * Column: order_no
     * Remark: 订单编号
     */
    private String orderNo;

    /**
     * Column: order_title
     * Remark: 订单标题
     */
    private String orderTitle;

    /**
     * Column: service_id
     * Remark: //使用什么服务导致的交易，如发红包服务、转账服务等等
     */
    private String serviceId;

    /**
     * Column: service_name
     * Remark: 服务名
     */
    private String serviceName;

    /**
     * Column: note
     */
    private String note;

    /**
     * Column: pay_sn
     * Remark: 关联的支付单号，一个支付单有且只有一条支付明细记录
     */
    private String paySn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPayeeCode() {
        return payeeCode;
    }

    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode == null ? null : payeeCode.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public String getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType == null ? null : payeeType.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle == null ? null : orderTitle.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getPaySn() {
        return paySn;
    }

    public void setPaySn(String paySn) {
        this.paySn = paySn == null ? null : paySn.trim();
    }
}