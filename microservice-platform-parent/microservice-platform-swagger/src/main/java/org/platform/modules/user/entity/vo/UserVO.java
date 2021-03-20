package org.platform.modules.user.entity.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("用户ID")
	private Long id = null;
	@ApiModelProperty("账号/警官证号")
	private String account = null;
	@ApiModelProperty("真实姓名")
	private String realName = null;
	@ApiModelProperty("手机号码")
	private String mobilePhone = null;
	@ApiModelProperty("用户类型 0 普通 1 管理员")
	private Integer type = null;
	@ApiModelProperty("创建时间")
	private Date createTime = null;
	@ApiModelProperty("启用冻结状态 true false")
	private Boolean enabledStatus = null;
	@ApiModelProperty("单位名称")
	private String companyName = null;

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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

	public Boolean getEnabledStatus() {
		return enabledStatus;
	}

	public void setEnabledStatus(Boolean enabledStatus) {
		this.enabledStatus = enabledStatus;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
