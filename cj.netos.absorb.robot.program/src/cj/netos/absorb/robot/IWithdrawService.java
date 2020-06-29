package cj.netos.absorb.robot;

import cj.studio.ecm.net.CircuitException;

public interface IWithdrawService {
    void doRequest(String bankid, String shunter, String alias, long amount) throws CircuitException;

    void doResponse(BankWithdrawResult result)throws CircuitException;

    void error(String sn,String status, String message);

}
