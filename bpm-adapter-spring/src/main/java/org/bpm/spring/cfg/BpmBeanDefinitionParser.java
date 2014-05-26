package org.bpm.spring.cfg;

import org.bpm.spring.BpmEngineFactory;
import org.bpm.spring.ProcessEngineType;
import org.bpm.spring.cfg.initialize.DynamicBeanInitialize;
import org.bpm.spring.cfg.initialize.MybatisInitalize;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.*;
import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * Created by izerui.com on 14-5-16.
 */
public class BpmBeanDefinitionParser implements BeanDefinitionParser {

    private final static String ATTRIBUTE_ID = "id";
    private final static String ATTRIBUTE_DATASOURCE = "dataSource";
    private final static String ATTRIBUTE_TRANSACTIONMANAGER = "transactionManager";
    private final static String ATTRIBUTE_PROCESSENGINETYPE = "processEngineType";
    private final static String ATTRIBUTE_CONFIGURATION = "configuration";
    private final static String ATTRIBUTE_BPMRUNTIMEBEANNAME = "bpmRuntimeBeanName";
    private final static String ATTRIBUTE_BPMDEFINITIONBEANNAME = "bpmDefinitionBeanName";


    @Override
    public synchronized BeanDefinition parse(Element element, ParserContext parserContext) {
        // 从标签中取出对应的属性值
        String id = element.getAttribute(ATTRIBUTE_ID);
        String dataSource = element.getAttribute(ATTRIBUTE_DATASOURCE);
        String transactionManager = element.getAttribute(ATTRIBUTE_TRANSACTIONMANAGER);
        String bpmRuntimeBeanName = element.getAttribute(ATTRIBUTE_BPMRUNTIMEBEANNAME);
        String bpmDefinitionBeanName = element.getAttribute(ATTRIBUTE_BPMDEFINITIONBEANNAME);
        ProcessEngineType processEngineType = ProcessEngineType.getProcessEngineType(element.getAttribute(ATTRIBUTE_PROCESSENGINETYPE));

        //注册配置管理器
        BeanDefinitionBuilder configuration = genericBeanDefinition(Configuration.class);
        configuration.addPropertyReference(ATTRIBUTE_DATASOURCE, dataSource);
        configuration.addPropertyReference(ATTRIBUTE_TRANSACTIONMANAGER, transactionManager);
        configuration.addPropertyValue(ATTRIBUTE_PROCESSENGINETYPE,processEngineType );
        parserContext.getRegistry().registerBeanDefinition(Configuration.class.getName(),configuration.getBeanDefinition());


        //注册bpmEngine 引擎工厂
        BeanDefinitionBuilder bpmEngineFactory = rootBeanDefinition(BpmEngineFactory.class);
        bpmEngineFactory.setDestroyMethodName("destroy");
        bpmEngineFactory.addPropertyReference(ATTRIBUTE_CONFIGURATION,Configuration.class.getName());
        parserContext.getRegistry().registerBeanDefinition(id,bpmEngineFactory.getBeanDefinition());


        //注册bpm bean
        registerBpmAPI(bpmRuntimeBeanName,id,"getBpmRuntime",processEngineType.getBpmRuntimeImplClass(),parserContext);
        registerBpmAPI(bpmDefinitionBeanName,id,"getBpmDefinition",processEngineType.getBpmDefinitionImplClass(),parserContext);


        //注册mybatis
        new MybatisInitalize(dataSource,transactionManager).registryBeans(parserContext.getRegistry());
        //注册activiti / jbpm6
        processEngineType.getProcessEngineInitialize(dataSource,transactionManager).registryBeans(parserContext.getRegistry());
        //注册动态bean
        new DynamicBeanInitialize(processEngineType).registryBeans(parserContext.getRegistry());


        return null;



    }

    private void registerBpmAPI(String beanName,String factoryBeanName , String factoryMethod , Class beanClass,ParserContext parserContext){
        BeanDefinitionBuilder builder = genericBeanDefinition(beanClass);
        builder.getRawBeanDefinition().setFactoryBeanName(factoryBeanName);
        builder.getRawBeanDefinition().setFactoryMethodName(factoryMethod);
        builder.getRawBeanDefinition().setPrimary(true);
        parserContext.getRegistry().registerBeanDefinition(beanName, builder.getBeanDefinition());
    }
}
