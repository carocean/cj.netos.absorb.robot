package cj.netos.absorb.robot;

import cj.studio.ecm.net.CircuitException;

public interface IFissionMFOutAbsorbService {
    void receive(BankWithdrawResult result) throws CircuitException;

}
