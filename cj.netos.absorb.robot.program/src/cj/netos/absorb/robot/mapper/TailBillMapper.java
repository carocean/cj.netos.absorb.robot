package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.TailBill;
import cj.netos.absorb.robot.model.TailBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TailBillMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TailBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TailBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TailBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TailBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TailBill> selectByExample(TailBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TailBill selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TailBill record, @Param("example") TailBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TailBill record, @Param("example") TailBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TailBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TailBill record);
}