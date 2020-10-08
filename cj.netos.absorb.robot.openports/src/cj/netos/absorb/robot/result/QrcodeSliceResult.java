package cj.netos.absorb.robot.result;

import cj.netos.absorb.robot.model.QrcodeSlice;
import cj.netos.absorb.robot.model.SliceProp;

import java.util.List;

public class QrcodeSliceResult extends QrcodeSlice {
    List<SliceProp> properties;


    public List<SliceProp> getProperties() {
        return properties;
    }

    public void setProperties(List<SliceProp> properties) {
        this.properties = properties;
    }


    public static QrcodeSliceResult createBy(QrcodeSlice slice, List<SliceProp> props) {
        QrcodeSliceResult r = new QrcodeSliceResult();
        r.setProperties(props);
        r.setBatchNo(slice.getBatchNo());
        r.setConsumer(slice.getConsumer());
        r.setCreator(slice.getCreator());
        r.setCtime(slice.getCtime());
        r.setExpire(slice.getExpire());
        r.setHref(slice.getHref());
        r.setCname(slice.getCname());
        r.setId(slice.getId());
        r.setLocation(slice.getLocation());
        r.setRadius(slice.getRadius());
        r.setMaxAbsorbers(slice.getMaxAbsorbers());
        r.setNote(slice.getNote());
        r.setOriginAbsorber(slice.getOriginAbsorber());
        r.setOriginPerson(slice.getOriginPerson());
        r.setState(slice.getState());
        r.setTemplate(slice.getTemplate());
        return r;
    }
}
