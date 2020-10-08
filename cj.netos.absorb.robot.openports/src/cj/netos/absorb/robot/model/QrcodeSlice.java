package cj.netos.absorb.robot.model;

/**
 * Table: qrcode_slice
 */
public class QrcodeSlice {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: href
     * Remark: 码片扫码后跳转的地址
     */
    private String href;

    /**
     * Column: creator
     * Remark: 码片创建者
     */
    private String creator;

    /**
     * Column: cname
     * Remark: 创建人名字
     */
    private String cname;

    /**
     * Column: consumer
     * Remark: 码片消费者,只有消费了才有
     */
    private String consumer;

    /**
     * Column: template
     * Remark: 码片渲染模板
     */
    private String template;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    /**
     * Column: expire
     * Remark: 码片过期时间，如果=0表示永不过期
     */
    private Long expire;

    /**
     * Column: location
     * Remark: 码片生成时的位置，一般与origin有关，如果有猫，则使用猫的位置和半径，如果无猫，则使用creator所在的位置
     */
    private String location;

    /**
     * Column: radius
     * Remark: 码片生成时的半径，一般与origin有关，如果有猫，则使用猫的位置和半径，如果无猫，则使用creator所在的位置
     */
    private Long radius;

    /**
     * Column: max_absorbers
     * Remark: 所能承载的猫的最大数量，从模板复制该属性，不能为0 总数由此限定，通过模板定义的洇取器权重来计算发现的洇取器的子类数量
     */
    private Long maxAbsorbers;

    /**
     * Column: origin_absorber
     * Remark: 生成码片依据的猫（洇取器）的标识，如果有的话
     */
    private String originAbsorber;

    /**
     * Column: origin_person
     * Remark: 生成码片依据的公众，必有。不论猫是否为空
     */
    private String originPerson;

    /**
     * Column: batch_no
     * Remark: 批次号，如果创建人的上一批次未发完，不充许再发码
     */
    private String batchNo;

    /**
     * Column: state
     * Remark: -1为未生效，即为默认状态，只有在添加洇取器的洇取人后才将此态更新为激活态0 0为激活态，即未被消费 1为已消费
     */
    private Integer state;

    /**
     * Column: note
     * Remark: 备注
     */
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer == null ? null : consumer.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Long getRadius() {
        return radius;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }

    public Long getMaxAbsorbers() {
        return maxAbsorbers;
    }

    public void setMaxAbsorbers(Long maxAbsorbers) {
        this.maxAbsorbers = maxAbsorbers;
    }

    public String getOriginAbsorber() {
        return originAbsorber;
    }

    public void setOriginAbsorber(String originAbsorber) {
        this.originAbsorber = originAbsorber == null ? null : originAbsorber.trim();
    }

    public String getOriginPerson() {
        return originPerson;
    }

    public void setOriginPerson(String originPerson) {
        this.originPerson = originPerson == null ? null : originPerson.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}