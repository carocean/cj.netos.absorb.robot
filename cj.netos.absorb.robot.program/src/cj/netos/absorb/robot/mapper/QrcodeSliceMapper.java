package cj.netos.absorb.robot.mapper;

import cj.netos.absorb.robot.model.QrcodeSlice;
import cj.netos.absorb.robot.model.QrcodeSliceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QrcodeSliceMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(QrcodeSliceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(QrcodeSliceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(QrcodeSlice record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(QrcodeSlice record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<QrcodeSlice> selectByExample(QrcodeSliceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    QrcodeSlice selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") QrcodeSlice record, @Param("example") QrcodeSliceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") QrcodeSlice record, @Param("example") QrcodeSliceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(QrcodeSlice record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(QrcodeSlice record);

    List<QrcodeSlice> page(@Param(value = "creator") String creator, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<QrcodeSlice> pageByBatch(@Param(value = "batchNo") String batchNo, @Param(value = "creator") String creator, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    void updateState(@Param(value = "id") String id, @Param(value = "state") int state);

    void consume(@Param(value = "id") String id, @Param(value = "consumer") String consumer);
}