package org.platform.modules.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-platform-gateway", qualifier = "userZuulRemoteService", fallback = UserZuulRemoteServiceFallback.class)
public interface IUserZuulRemoteService {

	@RequestMapping(value = "/microservice-platform-nacos-provider1/user", method = RequestMethod.GET)
	public String readUser1(@RequestParam("account") String account, @RequestParam("password") String password);
	
}

@Component("userZuulRemoteServiceFallback")
class UserZuulRemoteServiceFallback implements IUserZuulRemoteService {

	@Override
	public String readUser1(String account, String password) {
		return null;
	}

}