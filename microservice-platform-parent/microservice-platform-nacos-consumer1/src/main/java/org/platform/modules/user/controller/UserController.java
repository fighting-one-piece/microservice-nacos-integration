package org.platform.modules.user.controller;

import org.platform.modules.user.service.IUserRemoteService;
import org.platform.modules.user.service.IUserZuulRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class UserController {

	@Autowired
	private RestTemplate restTemplate = null;

	@Autowired
	private LoadBalancerClient loadBalancerClient = null;

	@Autowired
	@Qualifier("userRemoteService")
	private IUserRemoteService userRemoteService = null;

	@Autowired
	@Qualifier("userZuulRemoteService")
	private IUserZuulRemoteService userZuulRemoteService = null;

	@Value("${user.username}")
	private String username = null;

	@Value("${user.password}")
	private String password = null;

	@RequestMapping(value = "/user/tmp1", method = RequestMethod.GET)
	public Object readUserTmp1(String account, String password) {
		System.out.println(String.format("consumer1 account:%s password:%s", account, password));
		return restTemplate.getForObject("http://microservice-platform-nacos-provider1/user?account={1}&password={2}", String.class, account, password);
	}

	@RequestMapping(value = "/user/tmp2", method = RequestMethod.GET)
	public Object readUserTmp2(String account, String password) {
		System.out.println(String.format("consumer1 account:%s password:%s", account, password));
		// 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
		ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-platform-nacos-provider1");
		String url = serviceInstance.getUri() + "/user?account={1}&password={2}";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class, account, password);
		return "Invoke : " + url + ", return : " + result;
	}

	@RequestMapping(value = "/user/tmp3", method = RequestMethod.GET)
	public String readUserTmp3(String account, String password) {
		System.out.println(String.format("consumer1 account:%s password:%s", account, password));
		return userRemoteService.readUser(account, password);
	}

	@RequestMapping(value = "/user/tmp4", method = RequestMethod.GET)
	public String readUserTmp4(String account, String password) {
		System.out.println(String.format("consumer1 account:%s password:%s", account, password));
		return userZuulRemoteService.readUser1(account, password);
	}

	@RequestMapping(value = "/user/config", method = RequestMethod.GET)
	public String readUserConfig() {
		System.out.println(String.format("consumer1 user config username:%s password:%s", username, password));
		return String.format("consumer1 user config username:%s password:%s", username, password);
	}

}