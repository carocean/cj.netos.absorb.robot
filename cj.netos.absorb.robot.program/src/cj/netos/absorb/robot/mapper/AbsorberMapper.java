package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.AbsorberExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

    List<Absorber> pageAnyByCreator(@Param(value = "creator") String creator, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<Absorber> pageByCreator(@Param(value = "creator") String creator, @Param(value = "type") int type, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<Absorber> pageByCreatorAndUsage(@Param(value = "creator") String creator, @Param(value = "usage") int usage, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<Absorber> pageAnyJioninAbsorberByUsage(@Param(value = "person") String person, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<Absorber> pageJioninAbsorberByUsage(@Param(value = "person") String person, @Param(value = "usage") int usage, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    void stop(@Param(value = "id") String id, @Param(value = "exitCause") String exitCause);

    void start(@Param(value = "id") String id);

    void updateLocation(@Param(value = "id") String id, @Param(value = "location") String location);

    void updateRadius(@Param(value = "id") String id, @Param(value = "radius") long radius);

    void updateAbsorbabler(@Param(value = "id") String id, @Param(value = "absorbabler") String absorbabler);

    void updateMaxRecipients(@Param(value = "id") String id, @Param(value = "maxRecipients") long maxRecipients);


}