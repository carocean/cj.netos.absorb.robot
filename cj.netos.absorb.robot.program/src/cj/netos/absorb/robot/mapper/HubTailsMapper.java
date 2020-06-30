package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.HubTails;
import cj.netos.absorb.robot.model.HubTailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HubTailsMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(HubTailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(HubTailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(HubTails record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(HubTails record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<HubTails> selectByExample(HubTailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    HubTails selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") HubTails record, @Param("example") HubTailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") HubTails record, @Param("example") HubTailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(HubTails record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(HubTails record);
}