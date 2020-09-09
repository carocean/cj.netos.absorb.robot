package cj.netos.absorb.robot;

import org.apache.curator.framework.CuratorFramework;

public interface ICuratorPathChecker {
    void check(CuratorFramework framework, String path) throws Exception;
}
