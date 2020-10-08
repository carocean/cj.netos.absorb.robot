package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.SliceProp;
import cj.netos.absorb.robot.model.SlicePropExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SlicePropMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(SlicePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(SlicePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(@Param("qrcodeSlice") String qrcodeSlice, @Param("propId") String propId);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(SliceProp record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(SliceProp record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<SliceProp> selectByExample(SlicePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    SliceProp selectByPrimaryKey(@Param("qrcodeSlice") String qrcodeSlice, @Param("propId") String propId);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") SliceProp record, @Param("example") SlicePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") SliceProp record, @Param("example") SlicePropExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(SliceProp record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(SliceProp record);

    void update(@Param(value = "qrcodeSlice") String qrcodeSlice, @Param(value = "propId") String propId, @Param(value = "value") String value);
}