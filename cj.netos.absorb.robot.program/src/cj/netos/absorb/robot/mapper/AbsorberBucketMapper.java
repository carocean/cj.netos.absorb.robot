package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.TotalAbsorber;
import cj.netos.absorb.robot.model.AbsorberBucket;
import cj.netos.absorb.robot.model.AbsorberBucketExample;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

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
    int deleteByPrimaryKey(String absorber);

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
    AbsorberBucket selectByPrimaryKey(String absorber);

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

    /**
     * 获取洇取桶。条件：运行中的洇取器且大于等于指定价格
     *
     * @return
     */
    List<AbsorberBucket> pageAbsorberBucketGTEPrice(@Param(value = "bankid") String bankid, @Param(value = "price") BigDecimal price, @Param(value = "limit") int limit, @Param(value = "offset") int offset);

    List<AbsorberBucket> pageAbsorberBucket(@Param(value = "bankid") String bankid, @Param(value = "limit") int limit, @Param(value = "offset") int offset);

    TotalAbsorber totalAbsorber(@Param(value = "bankid") String bankid);

    void updateByPersonInvest(@Param(value = "absorber") String absorber, @Param(value = "pInvestAmount") BigDecimal pInvestAmount, @Param(value = "times") long times, @Param(value = "price") BigDecimal price, @Param(value = "utime") String utime);

    void updateByWithdrawInvest(@Param(value = "absorber") String absorber, @Param(value = "wInvestAmount") BigDecimal wInvestAmount, @Param(value = "times") long times, @Param(value = "price") BigDecimal price, @Param(value = "utime") String utime);

    TotalAbsorber totalAbsorberBeginWaaPrice(@Param(value = "bankid") String bankid, @Param(value = "price")BigDecimal price);

}