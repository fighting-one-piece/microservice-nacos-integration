package org.platform.utils.spring;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component("SpringBeanFactory")
public final class SpringBeanFactory implements BeanFactoryPostProcessor {

	/** Spring应用上下文环境*/
    private static ConfigurableListableBeanFactory beanFactory = null;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) 
    		throws BeansException {
    	if (null == SpringBeanFactory.beanFactory) {
    		SpringBeanFactory.beanFactory = beanFactory;
    	}
	}
    
    /**
     * 获取对象
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    @SuppressWarnings({ "unchecked" })
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     * @param clz
     * @return
     * @throws BeansException
     */
	public static <T> T getBean(Class<T> clz) throws BeansException {
        return (T) beanFactory.getBean(clz);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @param name
     * @return boolean
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.isSingleton(name);
    }

    /**
     * @param name
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getAliases(name);
    }
    
    /**
     * 给定bean类型返回所有bean列表
     * @param type
     * @return
     */
	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
    	return beanFactory.getBeansOfType(type);
    }
    
    /**
     * bean类型匹配
     * @param name
     * @param typeToMatch
     * @return
     */
    public static boolean isTypeMatch(String name, Class<?> typeToMatch) {
    	return beanFactory.isTypeMatch(name, typeToMatch);
    }

}
