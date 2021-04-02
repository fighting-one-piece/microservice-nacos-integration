package org.platform.modules.abstr.entity;

public enum ResultCode {

	SUCCESS(1, "操作成功"),
	FAILURE(2, "操作失败"),
	NOT_FOUNT_DATA(3, "未查询到数据"),
	SYSTEM_IS_BUSY(5, "系统繁忙，请稍后再试"),
	QUERY_FAILURE(7, "查询失败"),
	ILLEGAL_OPERATION(9,"非法操作"),

	PARAM_NULL(-101, "请求参数为空"),
	PARAM_ERROR(-102, "请求参数错误或非法请求"),
	PARAM_FORMAT_ERROR(-103, "请求参数格式错误"),
	
	DATA_EXISTED(-110, "数据已存在"),
	URL_MAPPING_ERROR(-120, "URL Mapping错误"),
	PAGE_NOT_FOUND(-130, "页面不存在"),
	
	DATABASE_CONNECTION_FAIL(-140, "数据库连接失败"),
	DATABASE_READ_FAIL(-141, "数据库读取失败"),
	DATABASE_OPERATION_FAIL(-142, "数据库操作失败"),
	
	USER_NOT_LOGIN(350, "用户未登录,请先登录"),
	RESOURCE_NOT_ACCESS(360, "当前资源无法访问"),
	
	SERVER_ERROR(500, "服务器端错误"),
	SERVER_REMOTE_ERROR(501, "远程服务器端错误"),
	SERVER_UNDER_MAINTENANCE(503, "服务器正在维护"),
	
	ACCOUNT_NOT_EXIST(600, "账号不存在"),
	ACCOUNT_PASSWORD_NOT_MATCH(601, "账号密码不匹配"),
	ACCOUNT_EXPIRED_OR_DELETED(602, "账户已过期或已删除"),
	ACCOUNT_NO_PERMISSION(603, "账户无权限"),
	IP_NOT_BINDING(611, "当前IP未绑定"),
	IP_NOT_ACCESS(612, "当前IP禁止访问"),
	ACCESSTOKEN_EXPIRED(620, "Token过期"),
	ACCESSTOKEN_INVALID(621, "Token无效"),

	MACHINE_CODE_NOT_MATCH(380,"机器码不匹配"),
	AUTHORIZATION_FAILURE(390,"授权失败"),
	AUTHORIZATION_EXPIRE(391,"授权已过期"),
	AUTHORIZATION_FILE_NOT_FOUNT(392,"未找到授权文件"),
	AUTHORIZATION_FILE_FORMAT_ERROR(393,"授权文件格式错误"),
	ENCRYPTION_LOCK_NOT_FOUNT(395,"未检测到USB加密狗");

	/** 代码值*/
	private int code = 0;
	/** 代码值描述*/
	private String desc = null;
	
	private ResultCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
