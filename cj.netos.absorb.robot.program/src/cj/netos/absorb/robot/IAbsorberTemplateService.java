package cj.netos.absorb.robot;

import cj.netos.absorb.robot.bo.AbsorberTemplate;
import cj.studio.ecm.net.CircuitException;

public interface IAbsorberTemplateService {
    AbsorberTemplate getTemplate();

    void refresh() throws CircuitException;
}
