package cj.netos.absorb.robot.model;

/**
 * Table: recipients
 */
public class Recipients {
    /**
     * Column: id
     * Remark: 标识
     */
    private String id;

    /**
     * Column: person
     * Remark: 公号
     */
    private String person;

    /**
     * Column: absorber
     * Remark: 所属的洇取器
     */
    private String absorber;

    /**
     * Column: person_name
     * Remark: 姓名
     */
    private String personName;

    /**
     * Column: ctime
     */
    private String ctime;

    /**
     * Column: weight
     * Remark: 权重 派发程序的算法是： 本次发的钱=金额*（个人的权重/（总人数*每个人的权重之和））
     */
    private Integer weight;

    /**
     * Column: encourage_code
     * Remark: 激励代码，如 great like ingeo 等等
     */
    private String encourageCode;

    /**
     * Column: encourage_cause
     * Remark: 激励原因：如他点了赞或做了评论，或在地圈等等原因
     */
    private String encourageCause;

    /**
     * Column: desire_amount
     * Remark: 期望的激励金，如果达到激励金额则移除 激励金如果为0表示永远洇取洇金
     */
    private Long desireAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getAbsorber() {
        return absorber;
    }

    public void setAbsorber(String absorber) {
        this.absorber = absorber == null ? null : absorber.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getEncourageCode() {
        return encourageCode;
    }

    public void setEncourageCode(String encourageCode) {
        this.encourageCode = encourageCode == null ? null : encourageCode.trim();
    }

    public String getEncourageCause() {
        return encourageCause;
    }

    public void setEncourageCause(String encourageCause) {
        this.encourageCause = encourageCause == null ? null : encourageCause.trim();
    }

    public Long getDesireAmount() {
        return desireAmount;
    }

    public void setDesireAmount(Long desireAmount) {
        this.desireAmount = desireAmount;
    }
}