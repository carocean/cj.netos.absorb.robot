package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.AbsorberExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AbsorberMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(AbsorberExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(AbsorberExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(Absorber record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(Absorber record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<Absorber> selectByExample(AbsorberExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    Absorber selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") Absorber record, @Param("example") AbsorberExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") Absorber record, @Param("example") AbsorberExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(Absorber record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(Absorber record);

    List<Absorber> pageAny(@Param(value = "bankid") String bankid, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<Absorber> pageWhere(@Param(value = "bankid") String bankid, @Param(value = "type") int type, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    BigDecimal totalWeightsOfAbsorber(@Param(value = "bankid") String bankid);

    void updateCurrent(@Param(value = "id") String id, @Param(value = "currentTimes") long currentTimes, @Param(value = "currentAmount") BigDecimal currentAmount);

    void stop(@Param(value = "id") String id, @Param(value = "exitCause") String exitCause);

    void updateWeight(@Param(value = "id") String id, @Param(value = "weight") BigDecimal weight);

}