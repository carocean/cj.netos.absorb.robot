package cj.netos.absorb.robot.result;

import cj.netos.absorb.robot.model.TemplateProp;

public class TemplatePropResult {
    String id;
    String name;
    String value;
    String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TemplateProp createProp(String template) {
        TemplateProp prop = new TemplateProp();
        prop.setId(id);
        prop.setName(name);
        prop.setValue(value);
        prop.setNote(note);
        prop.setTemplate(template);
        return prop;
    }
}
