package cj.netos.absorb.robot;

import cj.netos.absorb.robot.model.Absorber;
import cj.studio.ecm.net.CircuitException;

public interface IInvestService {
    void doResponse(Absorber absorber, WalletPayRecord result) throws CircuitException;

}
