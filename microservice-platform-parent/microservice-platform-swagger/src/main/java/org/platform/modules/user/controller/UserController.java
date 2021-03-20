package org.platform.modules.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.platform.modules.abstr.annotation.ApiV1RestController;
import org.platform.modules.abstr.entity.QueryResult;
import org.platform.modules.abstr.entity.Result;
import org.platform.modules.user.entity.User;
import org.platform.modules.user.entity.vo.UserVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="用户模块", tags={"用户接口"})
@ApiV1RestController
public class UserController {

	@ApiOperation(value = "新增用户", notes = "新增用户")
	@RequestMapping(value = "/user/insert", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType= "form", name = "account", value = "账号/警官证号", required = true, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "password", value = "密码", required = true, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "realName", value = "真实姓名", required = false, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "mobilePhone", value = "手机号码", required = false, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "companyName", value = "单位名称", required = false, dataType = "String")
	})
	public Result insert(String account, String password, String realName, String mobilePhone, String companyName) {
		return Result.buildSuccess(1L);
	}
	
	@ApiOperation(value = "更新用户", notes = "更新用户")
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType= "form", name = "id", value = "ID", required = true, dataType = "Long", example = "1"),
		@ApiImplicitParam(paramType= "form", name = "password", value = "密码", required = true, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "realName", value = "真实姓名", required = false, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "mobilePhone", value = "手机号码", required = false, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "companyName", value = "单位名称", required = false, dataType = "String")
	})
	public Result update(Long id, String password, String realName, String mobilePhone, String companyName) {
		return Result.buildSuccess();
	}
	
	@ApiOperation(value = "更新用户密码", notes = "更新用户密码")
	@RequestMapping(value = "/user/password/update", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType= "form", name = "id", value = "ID", required = true, dataType = "Long", example = "1"),
		@ApiImplicitParam(paramType= "form", name = "oldPassword", value = "旧密码", required = true, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "newPassword", value = "新密码", required = true, dataType = "String"),
		@ApiImplicitParam(paramType= "form", name = "confirmNewPassword", value = "确认新密码", required = true, dataType = "String")
	})
	public Result updatePassword(Long id, String oldPassword, String newPassword, String confirmNewPassword) {
		return Result.buildSuccess();
	}
	
	@ApiOperation(value = "更新用户冻结状态", notes = "更新用户冻结状态")
	@RequestMapping(value = "/user/enabledstatus/update", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType= "form", name = "id", value = "ID", required = true, dataType = "Long", example = "1"),
		@ApiImplicitParam(paramType= "form", name = "enabledStatus", value = "冻结状态", required = true, dataType = "Boolean")
	})
	public Result updateEnabledStatus(Long id, Boolean enabledStatus) {
		return Result.buildSuccess();
	}
	
	@ApiOperation(value = "删除用户", notes = "删除用户")
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType= "form", name = "id", value = "ID", required = true, dataType = "Long", example = "1")
	})
	public Result deleteById(Long id) {
		return Result.buildSuccess();
	}

	@ApiOperation(value = "读取当前用户信息", notes = "读取当前用户信息", response = UserVO.class)
	@RequestMapping(value = "/user/current", method = RequestMethod.GET)
	public Result readCurrentUser() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(User.Attribute.ID, 1L);
		result.put(User.Attribute.TYPE, 1);
		result.put(User.Attribute.ACCOUNT, "账号/警官证号");
		result.put(User.Attribute.REAL_NAME, "真实姓名1");
		result.put(User.Attribute.MOBILE_PHONE, "13512345678");
		result.put(User.Attribute.ENABLED_STATUS, true);
		result.put(User.Attribute.COMPANY_NAME, "单位名称1");
		result.put(User.Attribute.CREATE_TIME, new Date());
		return Result.buildSuccess(result);
	}
	
	@ApiOperation(value = "根据条件读取用户分页信息", notes = "根据条件读取用户分页信息", response = UserVO.class)
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType= "query", name = "account", value = "账号/警官证号", required = false, dataType = "String"),
		@ApiImplicitParam(paramType= "query", name = "realName", value = "真实姓名", required = false, dataType = "String"),
		@ApiImplicitParam(paramType= "query", name = "mobilePhone", value = "手机号码", required = false, dataType = "String"),
		@ApiImplicitParam(paramType= "query", name = "companyName", value = "单位名称", required = false, dataType = "String"),
		@ApiImplicitParam(paramType= "query", name = "currentPageNum", value = "当前页数", required = true, dataType = "Long", example = "1"),
		@ApiImplicitParam(paramType= "query", name = "rowNumPerPage", value = "每页行数", required = true, dataType = "Long", example = "1")
	})
	public Result readUsers(String account, String realName, String mobilePhone,
			Integer currentPageNum, Integer rowNumPerPage) {
		QueryResult<Map<String, Object>> qr = new QueryResult<Map<String,Object>>();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> result1 = new HashMap<String, Object>();
		result1.put(User.Attribute.ID, 1L);
		result1.put(User.Attribute.ACCOUNT, "账号/警官证号");
		result1.put(User.Attribute.REAL_NAME, "真实姓名1");
		result1.put(User.Attribute.MOBILE_PHONE, "13512345678");
		result1.put(User.Attribute.COMPANY_NAME, "单位名称1");
		resultList.add(result1);
		qr.setTotalRowNum(100L);
		qr.setResultList(resultList);
		return Result.buildSuccess(qr);
	}
	
}
