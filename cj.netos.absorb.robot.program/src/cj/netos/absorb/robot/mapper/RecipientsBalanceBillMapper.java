package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.RecipientsBalanceBill;
import cj.netos.absorb.robot.model.RecipientsBalanceBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecipientsBalanceBillMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(RecipientsBalanceBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(RecipientsBalanceBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(RecipientsBalanceBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(RecipientsBalanceBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<RecipientsBalanceBill> selectByExample(RecipientsBalanceBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    RecipientsBalanceBill selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") RecipientsBalanceBill record, @Param("example") RecipientsBalanceBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") RecipientsBalanceBill record, @Param("example") RecipientsBalanceBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(RecipientsBalanceBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(RecipientsBalanceBill record);
}