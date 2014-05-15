package org.bpm.spring.initialize.init;

import org.springframework.beans.factory.support.AbstractBeanDefinition;

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


}
