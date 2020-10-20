package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.TemplateProp;
import cj.netos.absorb.robot.model.TemplatePropExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplatePropMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TemplatePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TemplatePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(@Param("id") String id, @Param("template") String template);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TemplateProp record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TemplateProp record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TemplateProp> selectByExample(TemplatePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TemplateProp selectByPrimaryKey(@Param("id") String id, @Param("template") String template);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TemplateProp record, @Param("example") TemplatePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TemplateProp record, @Param("example") TemplatePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TemplateProp record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TemplateProp record);
}