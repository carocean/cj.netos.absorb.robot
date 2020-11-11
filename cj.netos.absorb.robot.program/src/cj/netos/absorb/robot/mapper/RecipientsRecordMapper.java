package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.RecipientsRecordExample;
import cj.netos.absorb.robot.result.RecipientsRecordInfoResult;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

public interface RecipientsRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(RecipientsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(RecipientsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(RecipientsRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(RecipientsRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<RecipientsRecord> selectByExample(RecipientsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    RecipientsRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") RecipientsRecord record, @Param("example") RecipientsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") RecipientsRecord record, @Param("example") RecipientsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(RecipientsRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(RecipientsRecord record);

    BigDecimal totalRecipientsRecord(@Param(value = "absorber") String absorber, @Param(value = "recipient") String recipient);

    BigDecimal totalRecipientsRecordById(@Param(value = "recipientsId") String recipientsId);

    List<RecipientsRecord> pageByPerson(@Param(value = "absorber") String absorber, @Param(value = "recipient") String recipient, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<RecipientsRecord> pageByRecipientsId(@Param(value = "recipientsId") String recipientsId, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    BigDecimal totalRecipientsRecordWhere(@Param(value = "absorber") String absorber, @Param(value = "recipientsId") String recipientsId);

    List<RecipientsRecord> pageRecipientsWhere(@Param(value = "absorber") String absorber, @Param(value = "recipientsId") String recipientsId, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<RecipientsRecord> pageRecipientsRecordByOrderWhere(@Param(value = "absorber") String absorber, @Param(value = "recipientsId") String recipientsId, @Param(value = "order") int order, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    BigDecimal totalRecipientsRecordByOrderWhere(@Param(value = "absorber") String absorber, @Param(value = "recipientsId") String recipientsId, @Param(value = "order") int order);

    List<RecipientsRecord> pageRecipientsRecordByOrderWhere2(@Param(value = "absorber") String absorber, @Param(value = "recipientsId") String recipientsId, @Param(value = "order") int order, @Param(value = "year") int year, @Param(value = "month") int month, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    BigDecimal totalRecipientsRecordByOrderWhere2(@Param(value = "absorber") String absorber, @Param(value = "recipientsId") String recipientsId, @Param(value = "order") int order, @Param(value = "year") int year, @Param(value = "month") int month);

    List<RecipientsRecord> pageRecipientsWhere3(@Param(value = "absorber") String absorber, @Param(value = "recipientsId") String recipientsId, @Param(value = "year") int year, @Param(value = "month") int month, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    RecipientsRecordInfoResult getRecipientsRecordInfo(@Param(value = "sn") String sn);

}