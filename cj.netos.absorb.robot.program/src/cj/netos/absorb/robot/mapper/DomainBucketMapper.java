package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.DomainBucket;
import cj.netos.absorb.robot.model.DomainBucketExample;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

public interface DomainBucketMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(DomainBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(DomainBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String bank);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(DomainBucket record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(DomainBucket record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<DomainBucket> selectByExample(DomainBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    DomainBucket selectByPrimaryKey(String bank);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") DomainBucket record, @Param("example") DomainBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") DomainBucket record, @Param("example") DomainBucketExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(DomainBucket record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(DomainBucket record);

    void updateWaaPrice(@Param(value = "bank") String bank, @Param(value = "waaPrice") BigDecimal waaPrice, @Param(value = "utime") String utime);
}