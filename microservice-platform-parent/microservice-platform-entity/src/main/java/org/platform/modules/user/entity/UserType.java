package org.platform.modules.user.entity;

public enum UserType {
	
	/** 普通 */
	COMMON(0),
	/** 管理员 */
	ADMIN(1);

	private int value = 0;
	
	private UserType(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
	
	public static boolean isAdmin(Integer value) {
		return isType(value, ADMIN);
	}
	
	public static boolean isType(Integer value, UserType userType) {
		return value != null && value == userType.value;
	}

}
