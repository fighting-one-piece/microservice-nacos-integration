package org.platform.modules.user.entity;

/** 用户属性表 */
public class UserAttribute {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private Long userId = null;
	/** 属性KEY */
	private String key = null;
	/** 属性VALUE */
	private String value = null;
	/** 属性类型 */
	private String type = null;
	/** 用户 */
	private transient User user = null;
	
	public UserAttribute() {}

	public UserAttribute(Long userId, String key, String value, String type) {
		super();
		this.userId = userId;
		this.key = key;
		this.value = value;
		this.type = type;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
