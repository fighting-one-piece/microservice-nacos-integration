package org.platform.modules.bootstrap.config;

import feign.*;
import feign.Retryer.Default;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableFeignClients(basePackages = { "org.platform.modules" })
public class FeignConfiguration {

	@Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
	
	@Bean
	public Retryer feignRetryer() {
		return new Default();
	}

	@Bean
	public Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
	
	@Bean
	public RequestInterceptor feignRequestInterceptor() {
		return new FeignRequestInterceptor();
	}
	
	/**
	HttpMessageConverters httpMessageConverters = new HttpMessageConverters();

	ObjectFactory<HttpMessageConverters> messageConvertersObjectFactory = new ObjectFactory<HttpMessageConverters>() {
		@Override
		public HttpMessageConverters getObject() throws BeansException {
			return httpMessageConverters;
		}
	};
	*/

	/**
	public Encoder feignEncoder() {
		return new SpringEncoder(messageConvertersObjectFactory);
	}
	*/

}

class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String requestUrl = requestTemplate.path();
        if (requestUrl.startsWith("/api")) requestUrl = requestUrl.replaceAll("/api/v.", "");
        if (!requestUrl.equals("/login") && !requestUrl.endsWith("/login")) {
            requestTemplate.header("accessToken", "");
        }
    }

}
