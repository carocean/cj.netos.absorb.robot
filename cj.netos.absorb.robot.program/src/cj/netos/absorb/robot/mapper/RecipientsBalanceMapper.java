package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.RecipientsBalance;
import cj.netos.absorb.robot.model.RecipientsBalanceExample;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

public interface RecipientsBalanceMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(RecipientsBalanceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(RecipientsBalanceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(RecipientsBalance record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(RecipientsBalance record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<RecipientsBalance> selectByExample(RecipientsBalanceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    RecipientsBalance selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") RecipientsBalance record, @Param("example") RecipientsBalanceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") RecipientsBalance record, @Param("example") RecipientsBalanceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(RecipientsBalance record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(RecipientsBalance record);

    void updateState(@Param(value = "qrcodeSlice") String qrcodeSlice, @Param(value = "recipients") String recipients, @Param(value = "state") int state);

    void updateBalance(@Param(value = "recipients") String recipients, @Param(value = "amount") BigDecimal amount);
}