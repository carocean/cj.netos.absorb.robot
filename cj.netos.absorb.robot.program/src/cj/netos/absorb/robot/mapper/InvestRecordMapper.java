package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.InvestRecordExample;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvestRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(InvestRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(InvestRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(InvestRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(InvestRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<InvestRecord> selectByExample(InvestRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    InvestRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") InvestRecord record, @Param("example") InvestRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") InvestRecord record, @Param("example") InvestRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(InvestRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(InvestRecord record);

    List<InvestRecord> pageInvestRecord(@Param(value = "absorber") String absorber,@Param(value = "limit") int limit, @Param(value = "offset") long offset);

    long totalAmountInvests(@Param(value = "absorber") String absorber);

}