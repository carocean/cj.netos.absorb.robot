package cj.netos.absorb.robot;

import cj.studio.ecm.net.CircuitException;

public interface IHubDistribute<T> {
    void distribute(T result) throws CircuitException;

}
