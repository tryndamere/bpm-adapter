package org.bpm.spring.initialize.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Created by izerui.com on 14-5-15.
 */
public abstract class AbstractBeanDefinitionImpl implements IBeanDefinition {

    protected final Logger log  = LoggerFactory.getLogger(getClass());

    protected DefaultListableBeanFactory beanFactory;

    public AbstractBeanDefinitionImpl(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
