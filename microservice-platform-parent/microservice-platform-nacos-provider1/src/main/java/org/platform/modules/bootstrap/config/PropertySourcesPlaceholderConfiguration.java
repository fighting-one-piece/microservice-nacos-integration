package org.platform.modules.bootstrap.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;

@Configuration
public class PropertySourcesPlaceholderConfiguration extends PropertySourcesPlaceholderConfigurer {

    private static ConfigurablePropertyResolver configurablePropertyResolver = null;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
            ConfigurablePropertyResolver propertyResolver) throws BeansException {
        super.processProperties(beanFactoryToProcess, propertyResolver);
        this.configurablePropertyResolver = propertyResolver;
    }

    public static String getProperty(String key) {
        return configurablePropertyResolver.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return configurablePropertyResolver.getProperty(key, defaultValue);
    }

    public static <T> T getProperty(String key, Class<T> targetType) {
        return configurablePropertyResolver.getProperty(key, targetType);
    }

    public static <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return configurablePropertyResolver.getProperty(key, targetType, defaultValue);
    }

}