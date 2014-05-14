package org.bpm.spring.initialize;

import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.bpm.engine.impl.activiti.ActivitiRuntimeImpl;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by serv on 14-5-14.
 */
public class ActivitiEngineInitialize {

    private Properties properties;
    private DefaultListableBeanFactory beanFactory;

    public ActivitiEngineInitialize(DefaultListableBeanFactory beanFactory, Properties properties){
        this.beanFactory = beanFactory;
        this.properties = properties;
    }


    public void init(){

        BeanDefinitionBuilder bpmRuntime = BeanDefinitionBuilder.rootBeanDefinition(ActivitiRuntimeImpl.class);
        bpmRuntime.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
        beanFactory.registerBeanDefinition("bpmRuntime", bpmRuntime.getBeanDefinition());


        //初始化activiti配置管理器
        BeanDefinitionBuilder sef = BeanDefinitionBuilder.rootBeanDefinition(SpringProcessEngineConfiguration.class);
        sef.addPropertyReference("dataSource",beanFactory.getBeanNamesForType(DataSource.class)[0]);
        sef.addPropertyReference("transactionManager", beanFactory.getBeanNamesForType(PlatformTransactionManager.class)[0]);
        sef.addPropertyValue("databaseSchemaUpdate", "true");
        sef.addPropertyValue("activityFontName",properties.getProperty("activityFontName"));
        sef.addPropertyValue("labelFontName",properties.getProperty("labelFontName"));
        sef.addPropertyValue("history",properties.getProperty("history"));
        sef.addPropertyValue("jobExecutorActivate",properties.getProperty("jobExecutorActivate"));
        beanFactory.registerBeanDefinition("processEngineConfiguration", sef.getBeanDefinition());

        //初始化activiti工厂
        BeanDefinitionBuilder bd = BeanDefinitionBuilder.rootBeanDefinition(ProcessEngineFactoryBean.class);
        bd.addPropertyReference("processEngineConfiguration","processEngineConfiguration");
        bd.setDestroyMethodName("destroy");
        beanFactory.registerBeanDefinition("processEngine", bd.getBeanDefinition());


        //初始化runtimeService
//        BeanDefinitionBuilder runtime = BeanDefinitionBuilder.rootBeanDefinition(RuntimeService.class);
//        AbstractBeanDefinition rawBeanDefinition = runtime.getRawBeanDefinition();
//        rawBeanDefinition.setFactoryBeanName("processEngine");
//        rawBeanDefinition.setFactoryMethodName("getRuntimeService");
//        beanFactory.registerBeanDefinition("runtimeService", runtime.getBeanDefinition());


    }

}
