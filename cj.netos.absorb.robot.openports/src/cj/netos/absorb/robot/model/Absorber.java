package cj.netos.absorb.robot.model;

import java.math.BigDecimal;

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
     * Remark: 洇取器类型, 0:简单洇取器 1:地理洇取器，是的时候其location和radius不为空
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
     * Column: exit_expire
     * Remark: 洇取器过期时间 0表示不限期 当达到过期时间则删除
     */
    private Long exitExpire;

    /**
     * Column: exit_amount
     * Remark: 限制的洇取金额，当达到该金额时删除洇取器 0表示洇取器不受限制的洇取资金
     */
    private Long exitAmount;

    /**
     * Column: weight
     * Remark: 洇取器权重。 一般固定洇取器权重高于一般的 权重比的调整只有地商才有权限调 算法：比重=1个洇取器的比重/（每个洇取器权重之和） 权重基数是1.00
     */
    private BigDecimal weight;

    /**
     * Column: max_recipients
     * Remark: 收取人数限制
     */
    private Long maxRecipients;

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

    public Long getExitExpire() {
        return exitExpire;
    }

    public void setExitExpire(Long exitExpire) {
        this.exitExpire = exitExpire;
    }

    public Long getExitAmount() {
        return exitAmount;
    }

    public void setExitAmount(Long exitAmount) {
        this.exitAmount = exitAmount;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Long getMaxRecipients() {
        return maxRecipients;
    }

    public void setMaxRecipients(Long maxRecipients) {
        this.maxRecipients = maxRecipients;
    }
}