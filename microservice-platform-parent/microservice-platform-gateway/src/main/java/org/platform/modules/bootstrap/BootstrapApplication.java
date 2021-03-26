package org.platform.modules.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableHystrix
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan(
	basePackages = {"org.platform"}
)
public class BootstrapApplication {

	protected static Logger LOG = LoggerFactory.getLogger(BootstrapApplication.class);

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
					.route(r -> r.path("/microservice-platform-nacos-provider1/**")
						.filters(f -> f.stripPrefix(1)
							.addRequestHeader("X-Custom-Header", "microservice-platform-nacos-provider1")
							.hystrix(consumer -> consumer.setName("provider1Hystrix")
								.setFallbackUri("forward:/microservice-platform-nacos-provider1/fallback")))
						.uri("lb://microservice-platform-nacos-provider1")
						.id("microservice-platform-nacos-provider1")
					)
					.route(r -> r.path("/microservice-platform-nacos-provider2/**")
						.uri("lb://microservice-platform-nacos-provider2")
						.id("/microservice-platform-nacos-provider2")
					)
				.build();
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(BootstrapApplication.class, args);
		LOG.info("Microservice Platform Gateway Bootstrap");
	}

}