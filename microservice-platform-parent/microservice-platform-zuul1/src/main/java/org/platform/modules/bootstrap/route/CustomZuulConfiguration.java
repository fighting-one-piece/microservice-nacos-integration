package org.platform.modules.bootstrap.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 配置路由和监听器 */
@Configuration
public class CustomZuulConfiguration {

    @Autowired
    private ZuulProperties zuulProperties = null;

    @Autowired
    private ServerProperties serverProperties = null;

    @Bean
    public CustomZuulRouteLocator zuulRouteLocator() {
        CustomZuulRouteLocator zuulRouteLocator = new CustomZuulRouteLocator(
            this.serverProperties.getServlet().getContextPath(), this.zuulProperties);
        return zuulRouteLocator;
    }

    @Bean
    public ApplicationListener<ApplicationEvent> zuulRefreshRouteListener() {
        return new CustomZuulRefreshRouteListener();
    }

}