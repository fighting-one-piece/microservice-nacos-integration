package org.platform.modules.system.entity;

import org.platform.modules.abstr.entity.PKAutoEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/** 操作日志表 */
@Table(name = "T_OPERATION_LOG")
public class OperationLog extends PKAutoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public interface Attribute {
        /** ID */
        public static final String ID = "id";
        /** 模块 */
        public static final String MODULE = "module";
        /** 功能 */
        public static final String FUNC = "func";
        /** 操作类型 */
        public static final String OP_TYPE = "opType";
    }

    /** 模块 */
    @Column(name = "MODULE")
    private String module = null;
    /** 功能 */
    @Column(name = "FUNC")
    private String func = null;
    /** 操作类型 */
    @Column(name = "OP_TYPE")
    private Integer opType = null;
    /** 操作目标类型 */
    @Column(name = "OP_TARGET_TYPE")
    private Integer opTargetType = null;
    /** 操作目标 */
    @Column(name = "OP_TARGET")
    private String opTarget = null;
    /** 备注 */
    @Column(name = "NOTE")
    private String note = null;
    /** 创建用户ID */
    @Column(name = "CREATE_USER_ID")
    private Long createUserId = null;
    /** 创建时间 */
    @Column(name = "CREATE_TIME")
    private Date createTime = null;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public Integer getOpTargetType() {
        return opTargetType;
    }

    public void setOpTargetType(Integer opTargetType) {
        this.opTargetType = opTargetType;
    }

    public String getOpTarget() {
        return opTarget;
    }

    public void setOpTarget(String opTarget) {
        this.opTarget = opTarget;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}