package cj.netos.absorb.robot;

import cj.netos.absorb.robot.bo.DomainBulletin;
import cj.netos.absorb.robot.model.AbsorberBucket;
import cj.studio.ecm.net.CircuitException;

import java.math.BigDecimal;

public interface IAbsorberDistribute {
    /**
     * 返回实际分出去的钱数
     *
     *
     * @param bucket
     * @param weightPricePerAbsorber
     * @param result
     * @return
     */
    BigDecimal distribute(AbsorberBucket bucket, BigDecimal weightPricePerAbsorber, Object result) throws CircuitException;


}
