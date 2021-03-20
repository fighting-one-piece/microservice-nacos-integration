package org.platform.modules.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String readUser(String account, String password) {
		System.out.println(String.format("provider1 account:%s password:%s", account, password));
		return String.format("provider1 account:%s password:%s", account, password);
	}
	
}