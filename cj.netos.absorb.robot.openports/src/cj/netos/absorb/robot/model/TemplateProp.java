package cj.netos.absorb.robot.model;

/**
 * Table: template_prop
 */
public class TemplateProp {
    /**
     * Column: id
     * Remark: 标识
     */
    private String id;

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
     * Column: template
     * Remark: 模板
     */
    private String template;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
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