package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.AbsorberBill;
import cj.netos.absorb.robot.model.AbsorberBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AbsorberBillMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(AbsorberBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(AbsorberBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(AbsorberBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(AbsorberBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<AbsorberBill> selectByExample(AbsorberBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    AbsorberBill selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") AbsorberBill record, @Param("example") AbsorberBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") AbsorberBill record, @Param("example") AbsorberBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(AbsorberBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(AbsorberBill record);
}