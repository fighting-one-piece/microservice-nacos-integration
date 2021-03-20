package org.platform.modules.system.entity;

import org.platform.modules.abstr.entity.PKAutoEntity;

import javax.persistence.*;

/** 操作日志属性表 */
@Entity
@Table(name = "T_OPERATION_LOG_ATTRIBUTE")
public class OperationLogAttribute extends PKAutoEntity<Long> {

	private static final long serialVersionUID = 1L;

	/** 操作日志ID */
	@Column(name = "OPERATION_LOG_ID")
	private Long operationLogId = null;
	/** 属性KEY */
	@Column(name = "KEY")
	private String key = null;
	/** 属性VALUE */
	@Column(name = "VALUE")
	private String value = null;
	/** 属性类型 */
	@Column(name = "TYPE")
	private String type = null;
	/** 操作日志 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "OPERATION_LOG_ID")
	private transient OperationLog operationLog = null;

	public OperationLogAttribute() {}

	public OperationLogAttribute(Long operationLogId, String key, String value, String type) {
		super();
		this.operationLogId = operationLogId;
		this.key = key;
		this.value = value;
		this.type = type;
	}

	public Long getOperationLogId() {
		return operationLogId;
	}

	public void setOperationLogId(Long operationLogId) {
		this.operationLogId = operationLogId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OperationLog getOperationLog() {
		return operationLog;
	}

	public void setOperationLog(OperationLog operationLog) {
		this.operationLog = operationLog;
	}

}