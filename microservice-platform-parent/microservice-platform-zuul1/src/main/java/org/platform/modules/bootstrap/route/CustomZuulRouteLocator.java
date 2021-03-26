package org.platform.modules.bootstrap.route;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.platform.modules.bootstrap.route.Constant.NACOS_DATA_ID;
import static org.platform.modules.bootstrap.route.Constant.NACOS_GROUP;

public class CustomZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private Logger LOG = LoggerFactory.getLogger(CustomZuulRouteLocator.class);

    @Autowired
    private ZuulProperties properties = null;

    @Autowired
    private ConfigService configService = null;

    public CustomZuulRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        Map<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
        // 从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        // 从nacos中加载路由信息
        routesMap.putAll(locateNacosRoutes());
        // 优化一下配置
        Map<String, ZuulRoute> values = new LinkedHashMap<String, ZuulRoute>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // prepend with slash if not already present
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulRoute> locateNacosRoutes() {
        Map<String, ZuulRoute> routes = new LinkedHashMap<String, ZuulRoute>();
        List<ZuulRouteEntity> nacosConfigRoutes = readNacosRouteConfig(NACOS_DATA_ID, NACOS_GROUP);
        for (ZuulRouteEntity nacosConfigRoute : nacosConfigRoutes) {
            if (org.apache.commons.lang.StringUtils.isBlank(nacosConfigRoute.getPath())
                /*|| org.apache.commons.lang.StringUtils.isBlank(result.getUrl())*/) {
                continue;
            }
            ZuulRoute zuulRoute = new ZuulRoute();
            try {
                BeanUtils.copyProperties(nacosConfigRoute, zuulRoute);
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }

    private List<ZuulRouteEntity> readNacosRouteConfig(String dataId, String group) {
        List<ZuulRouteEntity> zuulRoutes = new ArrayList<ZuulRouteEntity>();
        try {
            String configContent = configService.getConfig(dataId, group, 5000);
            LOG.info("nacos route config info {}", configContent);
            if (org.apache.commons.lang.StringUtils.isNotBlank(configContent)) {
                zuulRoutes = JSONObject.parseArray(configContent, ZuulRouteEntity.class);
            }
        } catch (NacosException e) {
            LOG.error(e.getMessage(), e);
        }
        return zuulRoutes;
    }

}