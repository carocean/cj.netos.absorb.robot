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
     * Column: category
     * Remark: 分类，如：喷泉、店、平聊、网流地微中文章、追链中的文章等等,以英文代码表示
     */
    private String category;

    /**
     * Column: proxy
     * Remark: 代表的对象的标识，如具体的文章标识，具体的实体店标识、具体的金证喷泉标识等 它与category对应，category说明是哪类实体 代表的对象如果是以地址表示的则指向其地址，没有的可为空
     */
    private String proxy;

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
     * Column: type
     * Remark: 洇取器类型, 0:简单洇取器 1:地理洇取器，是的时候其location和radius不为空 2:余额洇取器。是地理洇取器的一种，用于抢元宝
     */
    private Integer type;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy == null ? null : proxy.trim();
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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