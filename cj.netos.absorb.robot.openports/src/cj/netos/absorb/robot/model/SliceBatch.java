package cj.netos.absorb.robot.model;

/**
 * Table: slice_batch
 */
public class SliceBatch {
    /**
     * Column: id
     * Remark: 批号
     */
    private String id;

    /**
     * Column: creator
     * Remark: 创建者
     */
    private String creator;

    /**
     * Column: ctime
     * Remark: 生成时间
     */
    private String ctime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }
}