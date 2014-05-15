package org.bpm.spring.initialize;

import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.bpm.common.exception.impl.BusinessException;
import org.bpm.spring.initialize.init.AbstractBeanDefinitionImpl;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

/**
 * Created by serv on 14-5-14.
 */
public class ActivitiEngineInitialize extends AbstractBeanDefinitionImpl {

    private ResourcePatternResolver resourcePatternResolver;
    private Properties properties;

    public ActivitiEngineInitialize(DefaultListableBeanFactory beanFactory, ResourcePatternResolver resourcePatternResolver) {
        super(beanFactory);
        this.resourcePatternResolver = resourcePatternResolver;

        //根据流程引擎类型. 放入对应的属性值
        properties = new Properties();
        try {
            PropertiesLoaderUtils.fillProperties(
                    properties, resourcePatternResolver.getResource("classpath:activiti.properties"));
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            throw new BusinessException("加载流程引擎属性文件出错:"+e.getMessage(),e);
        }
    }


    @Override
    public Map<String, AbstractBeanDefinition> createBeanDefinitions() {
        Map<String, AbstractBeanDefinition> definitionMap = new HashMap<String, AbstractBeanDefinition>();
        definitionMap.put("processEngineConfiguration",createProcessEngineConfigurationDef());
        definitionMap.put("processEngine",createProcessEngine());

        return definitionMap;
    }




    private AbstractBeanDefinition createProcessEngineConfigurationDef(){
        BeanDefinitionBuilder sef = rootBeanDefinition(SpringProcessEngineConfiguration.class);
        sef.addPropertyReference("dataSource",beanFactory.getBeanNamesForType(DataSource.class)[0]);
        sef.addPropertyReference("transactionManager", beanFactory.getBeanNamesForType(PlatformTransactionManager.class)[0]);
        sef.addPropertyValue("databaseSchemaUpdate", "true");
        sef.addPropertyValue("activityFontName",properties.getProperty("activityFontName"));
        sef.addPropertyValue("labelFontName",properties.getProperty("labelFontName"));
        sef.addPropertyValue("history",properties.getProperty("history"));
        sef.addPropertyValue("jobExecutorActivate",properties.getProperty("jobExecutorActivate"));
        return sef.getBeanDefinition();
    }

    private AbstractBeanDefinition createProcessEngine(){
        BeanDefinitionBuilder bd = rootBeanDefinition(ProcessEngineFactoryBean.class);
        bd.addPropertyReference("processEngineConfiguration","processEngineConfiguration");
        bd.setDestroyMethodName("destroy");
        return bd.getBeanDefinition();
    }

}
