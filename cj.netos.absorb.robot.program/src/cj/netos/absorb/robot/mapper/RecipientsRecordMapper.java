package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.RecipientsRecord;
import cj.netos.absorb.robot.model.RecipientsRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}