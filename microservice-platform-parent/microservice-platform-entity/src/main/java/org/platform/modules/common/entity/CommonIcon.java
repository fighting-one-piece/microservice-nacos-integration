package org.platform.modules.common.entity;

import org.platform.modules.abstr.entity.PKAutoEntity;

import java.util.Date;

public class CommonIcon extends PKAutoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public interface Attribute {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TYPE = "type";
        public static final String PARENT_TYPE = "parentType";
        public static final String PATH = "path";
        public static final String NOTE = "note";
        public static final String INSERT_TIME = "insertTime";
    }
    /** 图标ID */
    private Long id = null;

    /** 图标名称 */
    private String name = null;

    /** 图标类型 */
    private String type = null;

    /** 图标父类型 */
    private String parentType = null;

    /** 图标路径 */
    private String path = null;

    /** 图标格式 */
    private String note = null;

    /** 图标上传时间 */
    private Date insertTime = null;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
