package org.platform.modules.bootstrap.route;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.concurrent.Executor;

import static com.alibaba.nacos.api.PropertyKeyConst.SERVER_ADDR;
import static org.platform.modules.bootstrap.route.Constant.NACOS_DATA_ID;
import static org.platform.modules.bootstrap.route.Constant.NACOS_GROUP;

@Configuration
public class NacosServerConfiguration {

    private Logger LOG = LoggerFactory.getLogger(NacosServerConfiguration.class);

    @Value("${spring.cloud.nacos.config.server-addr:127.0.0.1:8848}")
    private String serverAddr = null;
    @Autowired
    private CustomZuulRouteLocator zuulRouteLocator = null;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher = null;

    @Bean
    public ConfigService configService(){
        Properties properties = new Properties();
        properties.put(SERVER_ADDR, serverAddr);
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            configService.addListener(NACOS_DATA_ID, NACOS_GROUP, new Listener() {
                @Override
                public Executor getExecutor() {
                    //可以发送监听消息到某个MQ
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    LOG.info("Nacos Route Config Info Update!");
                    //切忌！！！不需要自己去刷新
                    RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(zuulRouteLocator);
                    applicationEventPublisher.publishEvent(routesRefreshedEvent);
                }
            });
            return configService;
        } catch (NacosException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

}