package org.platform.modules.bootstrap.route;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Component
public class NacosDynamicRoute implements ApplicationEventPublisherAware {

    private Logger LOG = LoggerFactory.getLogger(NacosDynamicRoute.class);

    @Value("${spring.cloud.nacos.config.server-addr:127.0.0.1:8848}")
    private String serverAddr = null;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter = null;

    private ApplicationEventPublisher applicationEventPublisher = null;

    private static final List<String> ROUTE_LIST = new ArrayList<String>();

    @PostConstruct
    public void dynamicRouteByNacosListener() {
        try {
            ConfigService configService = NacosFactory.createConfigService(serverAddr);
            configService.getConfig(Constant.NACOS_DATA_ID, Constant.NACOS_GROUP, 5000);
            configService.addListener(Constant.NACOS_DATA_ID, Constant.NACOS_GROUP, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    LOG.info("Nacos Config Info {}", configInfo);
                    clearRoute();
                    try {
                        List<RouteDefinition> gatewayRouteDefinitions = JSONObject.parseArray(configInfo, RouteDefinition.class);
                        for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
                            addRoute(routeDefinition);
                        }
                        publish();
                    } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                    }
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }

            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    private void addRoute(RouteDefinition definition) {
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            ROUTE_LIST.add(definition.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearRoute() {
        for(String id : ROUTE_LIST) {
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        }
        ROUTE_LIST.clear();
    }

    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}