package org.platform.modules.bootstrap.config;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import org.platform.modules.bootstrap.filter.CustomFilterProcessor;
import org.platform.modules.bootstrap.filter.ExtErrorFilter;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.IntrospectorCleanupListener;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Bean
	public CustomFilterProcessor CustomFilterProcessor() {
		return new CustomFilterProcessor();
	}
	
	@Bean
	public ExtErrorFilter extErrorFilter() {
		return new ExtErrorFilter();
	}
	
	@Bean
	public ServletListenerRegistrationBean<EventListener> requestContextListener(){
		ServletListenerRegistrationBean<EventListener> listenerRegistrationBean
			= new ServletListenerRegistrationBean<EventListener>();
		listenerRegistrationBean.setListener(new RequestContextListener());
//		registrationBean.setOrder(1);
		return listenerRegistrationBean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<EventListener> introspectorCleanupListener(){
		ServletListenerRegistrationBean<EventListener> listenerRegistrationBean
			= new ServletListenerRegistrationBean<EventListener>();
		listenerRegistrationBean.setListener(new IntrospectorCleanupListener());
		return listenerRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter(){
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean = new FilterRegistrationBean<CharacterEncodingFilter>();
		filterRegistrationBean.setFilter(characterEncodingFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");//?????????????????????????????????
		filterRegistrationBean.setUrlPatterns(urlPatterns);
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter(){
		FilterRegistrationBean<HiddenHttpMethodFilter> filterRegistrationBean = new FilterRegistrationBean<HiddenHttpMethodFilter>();
		filterRegistrationBean.setFilter(new HiddenHttpMethodFilter());
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");//?????????????????????????????????
		filterRegistrationBean.setUrlPatterns(urlPatterns);
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
		return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400.html"));            
				factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html"));            
				factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html"));        
				factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html"));        
			}
		};
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return objectMapper;
	}
	
}