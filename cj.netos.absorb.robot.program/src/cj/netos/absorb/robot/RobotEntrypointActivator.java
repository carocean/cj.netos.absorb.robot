package cj.netos.absorb.robot;

import cj.studio.ecm.EcmException;
import cj.studio.ecm.IEntryPointActivator;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.context.IElement;
import cj.studio.ecm.net.CircuitException;

public class RobotEntrypointActivator implements IEntryPointActivator {
    @Override
    public void activate(IServiceSite site, IElement args) {
        IAbsorberTemplateService templateService = (IAbsorberTemplateService) site.getService("absorberTemplateService");
        try {
            templateService.refresh();
        } catch (CircuitException e) {
            throw new EcmException(e);
        }
    }

    @Override
    public void inactivate(IServiceSite site) {

    }
}
