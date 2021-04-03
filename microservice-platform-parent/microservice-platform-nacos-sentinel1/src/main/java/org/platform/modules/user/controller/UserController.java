package org.platform.modules.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.platform.modules.user.service.IUserRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	@Qualifier("userRemoteService")
	private IUserRemoteService userRemoteService = null;

	@RequestMapping(value = "/api/v1/user/tmp1", method = RequestMethod.GET)
	@SentinelResource(value = "/user/tmp1", blockHandler = "readUserTmp1BlockHandler", fallback = "readUserTmp1FallbackHandler")
	public Object readUserTmp1(String account, String password) {
		System.out.println(String.format("sentienl user tmp1 account:%s password:%s", account, password));
		System.out.println(1/0);
		return String.format("sentienl user tmp1 account:%s password:%s", account, password);
	}

	public Object readUserTmp1BlockHandler(String account, String password, BlockException be) {
		LOG.error(String.format("BlockHandler：%s %s %s", account, password, be.getMessage()), be);
		return String.format("BlockHandler：%s %s", account, password);
	}

	public Object readUserTmp1FallbackHandler(String account, String password, Throwable e) {
		LOG.error(String.format("FallbackHandler：%s %s %s", account, password, e.getMessage()), e);
		return String.format("FallbackHandler：%s %s", account, password);
	}

	@RequestMapping(value = "/api/v1/user/tmp2", method = RequestMethod.GET)
	@SentinelResource(value = "/user/tmp2", blockHandler = "useBlockHandler", blockHandlerClass = UserSentinelExceptionHandler.class,
		fallback = "useFallbackHandler", fallbackClass = UserSentinelExceptionHandler.class)
	public Object readUserTmp2(String account, String password) {
		System.out.println(String.format("sentienl user tmp2 account:%s password:%s", account, password));
		System.out.println(1/0);
		return String.format("sentienl user tmp2 account:%s password:%s", account, password);
	}

	@RequestMapping(value = "/api/v1/user/tmp3", method = RequestMethod.GET)
	@SentinelResource(value = "/user/tmp3", blockHandler = "useBlockHandler", blockHandlerClass = UserSentinelExceptionHandler.class,
		defaultFallback = "useDefaultFallbackHandler", fallbackClass = UserSentinelExceptionHandler.class)
	public Object readUserTmp3(String account, String password) {
		System.out.println(String.format("sentienl user tmp3 account:%s password:%s", account, password));
		System.out.println(1/0);
		return String.format("sentienl user tmp3 account:%s password:%s", account, password);
	}

	@RequestMapping(value = "/api/v1/user/tmp4", method = RequestMethod.GET)
	@SentinelResource(value = "/user/tmp4", blockHandler = "useBlockHandler", blockHandlerClass = UserSentinelExceptionHandler.class,
		fallback = "useFallbackHandler", fallbackClass = UserSentinelExceptionHandler.class)
	public Object readUserTmp4(String account, String password) {
		System.out.println(String.format("sentienl user tmp4 account:%s password:%s", account, password));
		System.out.println(1/0);
		return userRemoteService.readUser(account, password);
	}

}