package org.bpm.spring.cfg.initialize;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Map;

/**
 * Created by izerui.com on 14-5-15.
 */
public interface IBeanDefinition {

    /**
     * 创建多个bean , key为beanname value 为beanDefinition
     * @return
     */
    public Map<String,AbstractBeanDefinition> createBeanDefinitions();

    /**
     * 注册bean
     */
    public void registryBeans(BeanDefinitionRegistry registry);


}
