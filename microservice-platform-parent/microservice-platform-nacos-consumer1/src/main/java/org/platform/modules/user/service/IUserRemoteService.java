package org.platform.modules.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-platform-nacos-provider1", qualifier = "userRemoteService", value = "microservice-platform-nacos-provider1", fallback = UserRemoteServiceFallback.class)
public interface IUserRemoteService {
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String readUser(@RequestParam("account") String account, @RequestParam("password") String password);
	
}

@Component("userRemoteServiceFallback")
class UserRemoteServiceFallback implements IUserRemoteService {

	@Override
	public String readUser(String account, String password) {
		return null;
	}

}