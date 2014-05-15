package org.bpm.spring.initialize.init;

import org.bpm.common.exception.impl.BusinessException;
import org.bpm.engine.Environment;
import org.bpm.spring.initialize.ActivitiEngineInitialize;
import org.bpm.spring.initialize.DynamicBeanInitialize;
import org.bpm.spring.initialize.MybatisInitalize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by izerui.com on 14-5-15.
 */
public class BeanManager {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private DefaultListableBeanFactory beanFactory;
    private String[] beanPackagesToScan;
    protected Map<String,AbstractBeanDefinition> beanDefinitions = new HashMap<String, AbstractBeanDefinition>();


    protected ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private String processEngineType = Environment.ACTIVITI_ENGINE_TYPE;

    public BeanManager buildBpmBeans() {


        if(processEngineType.equals(Environment.ACTIVITI_ENGINE_TYPE)){
            this.addBeans(new ActivitiEngineInitialize(this.beanFactory, resourcePatternResolver));
        }
        this.addBeans(new MybatisInitalize(this.beanFactory));
        this.addBeans(new DynamicBeanInitialize(this.beanFactory, resourcePatternResolver, beanPackagesToScan, processEngineType));

        return this;
    }


    private void addBeans(IBeanDefinition definition){

        Map<String,AbstractBeanDefinition> beanDefinitions = definition.createBeanDefinitions();

        this.beanDefinitions.putAll(beanDefinitions);

    }

    public void init(){

        if(beanFactory==null){
            throw new BusinessException("没有指定beanFactory");
        }

        for(String key : beanDefinitions.keySet()){

            AbstractBeanDefinition beanDefinition = beanDefinitions.get(key);

            beanFactory.registerBeanDefinition(key, beanDefinition);
        }
    }


    public Map<String, AbstractBeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }

    public void setBeanFactory(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String[] getBeanPackagesToScan() {
        return beanPackagesToScan;
    }

    public void setBeanPackagesToScan(String[] beanPackagesToScan) {
        this.beanPackagesToScan = beanPackagesToScan;
    }

    public void setProcessEngineType(String processEngineType) {
        this.processEngineType = processEngineType;
    }

    public String getProcessEngineType() {
        return processEngineType;
    }
}
