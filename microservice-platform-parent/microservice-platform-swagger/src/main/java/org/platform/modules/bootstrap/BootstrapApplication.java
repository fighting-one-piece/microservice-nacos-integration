package org.platform.modules.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "org.platform" })
public class BootstrapApplication extends SpringBootServletInitializer {

	protected static Logger LOG = LoggerFactory.getLogger(BootstrapApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BootstrapApplication.class, args);
		LOG.info("Microservice Platform Swagger Api Bootstrap");
		LOG.info("please access http://ip:port/microservice-platform-swagger/swagger-ui.html");
	}
	
}