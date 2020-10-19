package cj.netos.absorb.robot.model;

/**
 * Table: slice_template
 */
public class SliceTemplate {
    /**
     * Column: id
     * Remark: 标识
     */
    private String id;

    /**
     * Column: name
     * Remark: 模板名
     */
    private String name;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    /**
     * Column: copyright
     * Remark: 版权信息
     */
    private String copyright;

    /**
     * Column: max_absorbers
     * Remark: 所能承载的猫的最大数量，如果为0表示系统随机分配 总数由此限定，通过模板定义的洇取器权重来计算发现的洇取器的子类数量
     */
    private Long maxAbsorbers;

    /**
     * Column: owner_weight
     * Remark: 持有人的洇取器权重
     */
    private Long ownerWeight;

    /**
     * Column: particip_weight
     * Remark: 参与的洇取器的权重
     */
    private Long participWeight;

    /**
     * Column: ingeo_weight
     * Remark: 在圈的洇取器权重。在qrcode_slice的location,radius之内的
     */
    private Long ingeoWeight;

    /**
     * Column: background
     * Remark: 背景 如果以#开头，表示背景色 如果以http开头，表示图
     */
    private String background;

    /**
     * Column: note
     */
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright == null ? null : copyright.trim();
    }

    public Long getMaxAbsorbers() {
        return maxAbsorbers;
    }

    public void setMaxAbsorbers(Long maxAbsorbers) {
        this.maxAbsorbers = maxAbsorbers;
    }

    public Long getOwnerWeight() {
        return ownerWeight;
    }

    public void setOwnerWeight(Long ownerWeight) {
        this.ownerWeight = ownerWeight;
    }

    public Long getParticipWeight() {
        return participWeight;
    }

    public void setParticipWeight(Long participWeight) {
        this.participWeight = participWeight;
    }

    public Long getIngeoWeight() {
        return ingeoWeight;
    }

    public void setIngeoWeight(Long ingeoWeight) {
        this.ingeoWeight = ingeoWeight;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background == null ? null : background.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}