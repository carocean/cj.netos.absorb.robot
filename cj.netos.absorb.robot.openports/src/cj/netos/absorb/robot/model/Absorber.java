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
     * Remark: 洇取器名称
     */
    private String title;

    /**
     * Column: type
     * Remark: 0为一般洇取器 1为地理洇取器
     */
    private Integer type;

    /**
     * Column: bankid
     * Remark: 向哪个行注册洇取器
     */
    private String bankid;

    /**
     * Column: category
     * Remark: 分类，如：喷泉、店、平聊、网流地微中文章的赞、评论，追链中的阅读等等
     */
    private String category;

    /**
     * Column: proxy
     * Remark: 洇取器代表的真实对象，一般为标识，如具体的文章标识，具体的实体店标识、具体的金证喷泉标识等 它与category对应，category说明是哪类实体
     */
    private String proxy;

    /**
     * Column: location
     * Remark: 位置，type=1才有用 经纬度json
     */
    private String location;

    /**
     * Column: radius
     * Remark: 位径，type=1才有用
     */
    private String radius;

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
    private String exitExpire;

    /**
     * Column: exit_amount
     * Remark: 限制的洇取金额，当达到该金额时删除洇取器
     */
    private Long exitAmount;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius == null ? null : radius.trim();
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

    public String getExitExpire() {
        return exitExpire;
    }

    public void setExitExpire(String exitExpire) {
        this.exitExpire = exitExpire == null ? null : exitExpire.trim();
    }

    public Long getExitAmount() {
        return exitAmount;
    }

    public void setExitAmount(Long exitAmount) {
        this.exitAmount = exitAmount;
    }
}