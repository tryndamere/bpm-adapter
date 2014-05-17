package org.bpm.spring.cfg.initialize;

import org.bpm.common.exception.impl.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Map;

/**
 * Created by izerui.com on 14-5-15.
 */
public abstract class AbstractBeanDefinitionImpl implements IBeanDefinition {

    protected final Logger log  = LoggerFactory.getLogger(getClass());


    @Override
    public void registryBeans(BeanDefinitionRegistry registry) {

        if (registry == null) {
            throw new BusinessException("没有指定registry");
        }

        Map<String, AbstractBeanDefinition> beanDefinitions = createBeanDefinitions();

        for (String key : beanDefinitions.keySet()) {

            AbstractBeanDefinition beanDefinition = beanDefinitions.get(key);

            registry.registerBeanDefinition(key, beanDefinition);
        }

    }



}
