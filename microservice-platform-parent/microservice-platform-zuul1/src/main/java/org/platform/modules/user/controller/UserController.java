package org.platform.modules.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String readUser(String account, String password) {
		return String.format("zuul1 account:%s password:%s", account, password);
	}
	
}