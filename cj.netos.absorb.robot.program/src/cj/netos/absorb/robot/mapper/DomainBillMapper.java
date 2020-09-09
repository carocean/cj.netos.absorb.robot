package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.DomainBill;
import cj.netos.absorb.robot.model.DomainBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DomainBillMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(DomainBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(DomainBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(DomainBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(DomainBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<DomainBill> selectByExample(DomainBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    DomainBill selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") DomainBill record, @Param("example") DomainBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") DomainBill record, @Param("example") DomainBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(DomainBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(DomainBill record);

    List<DomainBill> page(@Param(value = "bankid") String bankid, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<DomainBill> getBillOfMonth(@Param(value = "bankid") String bankid, @Param(value = "year") int year, @Param(value = "month") int month, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<DomainBill> pageBillOfMonth(@Param(value = "bankid") String bankid, @Param(value = "order") int order, @Param(value = "year") int year, @Param(value = "month") int month, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    String totalBillOfMonth(@Param(value = "bankid") String bankid, @Param(value = "year") int year, @Param(value = "month") int month);
}