package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.AbsorberBucket;
import cj.netos.absorb.robot.model.AbsorberBucketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AbsorberBucketMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(AbsorberBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(AbsorberBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(AbsorberBucket record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(AbsorberBucket record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<AbsorberBucket> selectByExample(AbsorberBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    AbsorberBucket selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") AbsorberBucket record, @Param("example") AbsorberBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") AbsorberBucket record, @Param("example") AbsorberBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(AbsorberBucket record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(AbsorberBucket record);
}