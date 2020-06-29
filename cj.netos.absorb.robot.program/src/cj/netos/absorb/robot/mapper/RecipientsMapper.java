package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.Recipients;
import cj.netos.absorb.robot.model.RecipientsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecipientsMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(RecipientsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(RecipientsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(Recipients record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(Recipients record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<Recipients> selectByExample(RecipientsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    Recipients selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") Recipients record, @Param("example") RecipientsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") Recipients record, @Param("example") RecipientsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(Recipients record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(Recipients record);
}