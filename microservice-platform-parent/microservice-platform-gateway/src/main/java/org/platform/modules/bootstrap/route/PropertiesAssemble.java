package org.platform.modules.bootstrap.route;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.platform.modules.bootstrap.route.Constant.NACOS_DATA_ID;
import static org.platform.modules.bootstrap.route.Constant.NACOS_GROUP;

@Component
public class PropertiesAssemble {

    private Logger LOG = LoggerFactory.getLogger(PropertiesAssemble.class);

    @Autowired
    private ConfigService configService = null;

    public Map<String, ZuulRoute> getProperties() {
        Map<String, ZuulRoute> routes = new LinkedHashMap<String, ZuulRoute>();
        List<ZuulRouteEntity> nacosConfigRoutes = readNacosConfig(NACOS_DATA_ID, NACOS_GROUP);
        for (ZuulRouteEntity nacosConfigRoute : nacosConfigRoutes) {
            if (StringUtils.isBlank(nacosConfigRoute.getPath())
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

    private List<ZuulRouteEntity> readNacosConfig(String dataId, String group) {
        List<ZuulRouteEntity> zuulRoutes = new ArrayList<ZuulRouteEntity>();
        try {
            String configContent = configService.getConfig(dataId, group, 5000);
            LOG.info("nacos config content {}", configContent);
            if (StringUtils.isNotBlank(configContent)) {
                zuulRoutes = JSONObject.parseArray(configContent, ZuulRouteEntity.class);
            }
        } catch (NacosException e) {
            LOG.error(e.getMessage(), e);
        }
        return zuulRoutes;
    }

}