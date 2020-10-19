package cj.netos.absorb.robot.model;

/**
 * Table: slice_prop
 */
public class SliceProp {
    /**
     * Column: qrcode_slice
     * Remark: 码片标识
     */
    private String qrcodeSlice;

    /**
     * Column: prop_id
     * Remark: 模板属性的标识
     */
    private String propId;

    /**
     * Column: name
     * Remark: 属性名
     */
    private String name;

    /**
     * Column: type
     * Remark: 属性类型 text 为文本 image为图片 color为颜色 href为地址 ……
     */
    private String type;

    /**
     * Column: value
     * Remark: 属性值
     */
    private String value;

    /**
     * Column: note
     * Remark: 描述
     */
    private String note;

    public String getQrcodeSlice() {
        return qrcodeSlice;
    }

    public void setQrcodeSlice(String qrcodeSlice) {
        this.qrcodeSlice = qrcodeSlice == null ? null : qrcodeSlice.trim();
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId == null ? null : propId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}