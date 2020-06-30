package cj.netos.absorb.robot;

import cj.netos.absorb.robot.model.Absorber;
import cj.studio.ecm.net.CircuitException;

import java.math.BigDecimal;

public interface IAbsorberDistribute {
    /**
     * 返回实际分出去的钱数
     *
     * @param absorber
     * @param weightPricePerAbsorber
     * @param result
     * @return
     */
    BigDecimal distribute( Absorber absorber, BigDecimal weightPricePerAbsorber, Object result) throws CircuitException;


}
