package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.bo.RecipientsSummary;
import cj.netos.absorb.robot.model.Recipients;
import cj.netos.absorb.robot.model.RecipientsExample;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

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

    BigDecimal totalWeightsOfRecipients(@Param(value = "absorber") String absorber);

    List<Recipients> page(@Param(value = "absorber") String absorber, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<RecipientsSummary> pageSummary(@Param(value = "absorber") String absorber, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    void updateWeight(@Param(value = "id") String id, @Param(value = "weight") BigDecimal weight);

    Long countRecipients(@Param(value = "absorber") String absorber);

    void updateWeights(@Param(value = "absorber") String absorber,@Param(value = "person")  String person,@Param(value = "encourageCode")  String encourageCode,@Param(value = "weight")  BigDecimal weight);

}