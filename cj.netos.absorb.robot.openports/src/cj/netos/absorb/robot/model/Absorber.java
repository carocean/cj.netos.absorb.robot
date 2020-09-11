package cj.netos.absorb.robot.model;

/**
 * Table: absorber
 */
public class Absorber {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: title
     * Remark: 洇取器名称 一般以proxy对应对象的名称命名
     */
    private String title;

    /**
     * Column: bankid
     * Remark: 向哪个行注册洇取器
     */
    private String bankid;

    /**
     * Column: type
     * Remark: 洇取器类型, 0:简单洇取器 1:地理洇取器，是的时候其location和radius不为空 2:余额洇取器。是地理洇取器的一种，用于抢元宝
     */
    private Integer type;

    /**
     * Column: usage
     * Remark: 用途： 0网流管道 1地理感知器 2街道 3金证喷泉 4抢元宝
     */
    private Integer usage;

    /**
     * Column: absorbabler
     * Remark: 可洇取物。管道和感知器以type/id格式表示，其它为id
     */
    private String absorbabler;

    /**
     * Column: location
     * Remark: 位置，经纬度json，格式为：{"latitude":%s,"longitude":%s}
     */
    private String location;

    /**
     * Column: radius
     * Remark: 半径，单位米
     */
    private Long radius;

    /**
     * Column: creator
     * Remark: 洇取器创建人
     */
    private String creator;

    /**
     * Column: ctime
     * Remark: 洇取器创建时间
     */
    private String ctime;

    /**
     * Column: max_recipients
     * Remark: 人数上限，0表示不限制
     */
    private Long maxRecipients;

    /**
     * Column: state
     * Remark: 0为停用，停用原因有：过期、达到金额、达到次数 1为洇取器可用
     */
    private Integer state;

    /**
     * Column: exit_cause
     * Remark: 停用原因。 由于洇取器永不删除，当洇取器过期、达到金额、达到次数时会被标记state=-1，此处说明原因，如下： - 过期 - 达到金额 - 达到次数 
     */
    private String exitCause;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }

    public String getAbsorbabler() {
        return absorbabler;
    }

    public void setAbsorbabler(String absorbabler) {
        this.absorbabler = absorbabler == null ? null : absorbabler.trim();
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public Long getMaxRecipients() {
        return maxRecipients;
    }

    public void setMaxRecipients(Long maxRecipients) {
        this.maxRecipients = maxRecipients;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExitCause() {
        return exitCause;
    }

    public void setExitCause(String exitCause) {
        this.exitCause = exitCause == null ? null : exitCause.trim();
    }
}