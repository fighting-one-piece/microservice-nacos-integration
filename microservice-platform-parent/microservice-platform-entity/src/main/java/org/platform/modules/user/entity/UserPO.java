package org.platform.modules.user.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserPO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private Long id = null;
	/** 用户账号 */
	private String account = null;
	/** 用户密码 */
	private String password = null;
	/** 盐值 */
	private String salt = null;
	/** 用户昵称 */
	private String nickName = null;
	/** 用户真名 */
	private String realName = null;
	/** 身份证号码 */
	private String idCard = null;
	/** 手机号码 */
	private String mobilePhone = null;
	/** 用户邮箱 */
	private String email = null;
	/** 用户类型 */
	private Integer type = null;
	/** 创建时间 */
	private Date createTime = null;
	/** 过期时间 */
	private Date expireTime = null;
	/** 是否删除标志 */
	private Boolean deleteFlag = null;
	/** 启用状态 */
	private Boolean enabledStatus = null;
	/** 密码重置状态 */
	private Boolean passwordResetStatus = null;
	/** 公司名称 */
	private String companyName = null;
	/** ACCESSTOKEN */
	private String accessToken = null;
	/** SESSIONID */
	private String sessionId = null;
	/** 用户属性列表 */
	private List<UserAttribute> attributes = null;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public boolean hasDeleted() {
		return deleteFlag;
	}

	public Boolean getEnabledStatus() {
		return enabledStatus;
	}

	public void setEnabledStatus(Boolean enabledStatus) {
		this.enabledStatus = enabledStatus;
	}

	public Boolean getPasswordResetStatus() {
		return passwordResetStatus;
	}

	public void setPasswordResetStatus(Boolean passwordResetStatus) {
		this.passwordResetStatus = passwordResetStatus;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<UserAttribute> getAttributes() {
		if (null == attributes) attributes = new ArrayList<UserAttribute>();
		return attributes;
	}

	public void setAttributes(List<UserAttribute> attributes) {
		this.attributes = attributes;
	}

	public boolean hasExpired() {
		return expireTime.before(Calendar.getInstance().getTime());
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
