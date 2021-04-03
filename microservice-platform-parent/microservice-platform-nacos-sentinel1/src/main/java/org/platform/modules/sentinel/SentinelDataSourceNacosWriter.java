package org.platform.modules.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Component
public class SentinelDataSourceNacosWriter {

    private Logger LOG = LoggerFactory.getLogger(SentinelDataSourceNacosWriter.class);

    @Value("${spring.cloud.sentinel.datasource.ds1.nacos.server-addr}")
    private String serverAddr = null;
    @Value("${spring.cloud.sentinel.datasource.ds1.nacos.group-id}")
    private String groupId = null;
    @Value("${spring.cloud.sentinel.datasource.ds1.nacos.data-id}")
    private String dataId = null;

    @PostConstruct
    public void postConstruct() throws NacosException {
        // 从Nacos配置中心读取限流规则
        // 创建NacosDataSource并将其注册至对应的RuleManager上
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<List<FlowRule>>(
            serverAddr, groupId, dataId, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>(){}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
        LOG.info("flow rule data source property {}", flowRuleDataSource.getProperty());
        // 自动写入 Sentinel Dashboard中修改规则同步到Nacos
        WritableDataSource writableDataSource = new NacosWritableDataSource<List<FlowRule>>(serverAddr, groupId, dataId);
        WritableDataSourceRegistry.registerFlowDataSource(writableDataSource);
    }

    /**
     * 手动写入规则
     *    "[\n"
     *    + "  {\n"
     *    + "    \"resource\": \"login\",\n"
     *    + "    \"controlBehavior\": 0,\n"
     *    + "    \"count\": 5.0,\n"
     *    + "    \"grade\": 1,\n"
     *    + "    \"limitApp\": \"default\",\n"
     *    + "    \"strategy\": 0\n"
     *    + "  }\n"
     *  + "]";
     * @param rule
     */
    public void manualWriteRule(String rule) {
        try {
            ConfigService configService = NacosFactory.createConfigService(serverAddr);
            boolean isPublish = configService.publishConfig(dataId, groupId, rule);
            LOG.info("nacos config public {} {}", rule, isPublish);
        } catch (NacosException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}

class NacosWritableDataSource<T> implements WritableDataSource<T> {

    private Logger LOG = LoggerFactory.getLogger(NacosWritableDataSource.class);

    private String serverAddr = null;
    private String groupId = null;
    private String dataId = null;

    public NacosWritableDataSource(String serverAddr, String groupId, String dataId) {
        this.serverAddr = serverAddr;
        this.groupId = groupId;
        this.dataId = dataId;
    }

    //Sentinel更新的时候
    @Override
    public void write(T value) throws Exception {
        String rule = JSON.toJSONString(value);
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, "public");
        ConfigService configService = NacosFactory.createConfigService(properties);
        boolean isPublish = configService.publishConfig(dataId, groupId, rule);
        LOG.info("nacos config public {} {}", rule, isPublish);
    }

    @Override
    public void close() throws Exception {
    }

}