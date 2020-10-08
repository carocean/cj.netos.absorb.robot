package cj.netos.absorb.robot.result;

import cj.netos.absorb.robot.model.SliceTemplate;
import cj.netos.absorb.robot.model.TemplateProp;

import java.util.ArrayList;
import java.util.List;

public class QrcodeSliceTemplateResult extends SliceTemplate {
    List<TemplatePropResult> properties;

    public static QrcodeSliceTemplateResult createBy(SliceTemplate template, List<TemplateProp> props) {
        QrcodeSliceTemplateResult o = new QrcodeSliceTemplateResult();
        o.setCopyright(template.getCopyright());
        o.setCtime(template.getCtime());
        o.setId(template.getId());
        o.setIngeoWeight(template.getIngeoWeight());
        o.setMaxAbsorbers(template.getMaxAbsorbers());
        o.setName(template.getName());
        o.setNote(template.getNote());
        o.setOwnerWeight(template.getOwnerWeight());
        o.setParticipWeight(template.getParticipWeight());
        List<TemplatePropResult> list = new ArrayList<>();
        if (props != null) {
            for (TemplateProp prop : props) {
                TemplatePropResult r = new TemplatePropResult();
                r.setNote(prop.getNote());
                r.setValue(prop.getValue());
                r.setName(prop.getName());
                r.setId(prop.getId());
                list.add(r);
            }
        }
        o.setProperties(list);
        return o;
    }

    public List<TemplatePropResult> getProperties() {
        return properties;
    }

    public void setProperties(List<TemplatePropResult> properties) {
        this.properties = properties;
    }
}
