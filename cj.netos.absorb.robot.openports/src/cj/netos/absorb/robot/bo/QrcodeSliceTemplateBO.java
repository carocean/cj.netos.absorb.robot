package cj.netos.absorb.robot.bo;

import cj.netos.absorb.robot.model.SliceTemplate;
import cj.netos.absorb.robot.model.TemplateProp;
import cj.netos.absorb.robot.result.TemplatePropResult;
import cj.netos.absorb.robot.util.IdWorker;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.studio.openport.util.Encript;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QrcodeSliceTemplateBO {
    String id;
    String name;
    String note;
    String background;
    String copyright;
    long maxAbsorbers;
    long ownerWeight;
    long participWeight;
    long ingeoWeight;
    List<TemplatePropResult> properties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public long getMaxAbsorbers() {
        return maxAbsorbers;
    }

    public void setMaxAbsorbers(long maxAbsorbers) {
        this.maxAbsorbers = maxAbsorbers;
    }

    public long getOwnerWeight() {
        return ownerWeight;
    }

    public void setOwnerWeight(long ownerWeight) {
        this.ownerWeight = ownerWeight;
    }

    public long getParticipWeight() {
        return participWeight;
    }

    public void setParticipWeight(long participWeight) {
        this.participWeight = participWeight;
    }

    public long getIngeoWeight() {
        return ingeoWeight;
    }

    public void setIngeoWeight(long ingeoWeight) {
        this.ingeoWeight = ingeoWeight;
    }

    public List<TemplatePropResult> getProperties() {
        return properties;
    }

    public void setProperties(List<TemplatePropResult> properties) {
        this.properties = properties;
    }

    public SliceTemplate createTemplate() {
        SliceTemplate template = new SliceTemplate();
        template.setCopyright(copyright);
        template.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        template.setId(id);
        template.setIngeoWeight(ingeoWeight);
        template.setMaxAbsorbers(maxAbsorbers);
        template.setName(name);
        template.setBackground(background);
        template.setOwnerWeight(ownerWeight);
        template.setParticipWeight(participWeight);
        template.setNote(note);
        return template;
    }

    public List<TemplateProp> createProps() {
        List<TemplateProp> list = new ArrayList<>();
        if (properties == null) {
            return list;
        }
        for (TemplatePropResult prop : properties) {
            list.add(prop.createProp(id));
        }
        return list;
    }
}
