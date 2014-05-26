package org.bpm.spring.cfg.initialize;

import org.bpm.common.exception.impl.BusinessException;
import org.bpm.common.stereotype.DynamicBean;
import org.bpm.common.Environment;
import org.bpm.spring.ProcessEngineType;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.*;

/**
* Created by izerui.com on 14-5-15.
*/
public class DynamicBeanInitialize extends AbstractBeanDefinitionImpl {

    private static final String BEAN_CLASS_RESOURCE_PATTERN = "/**/*.class";

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private String[] beanPackagesToScan = new String[]{Environment.REPOSITORY_PACKAGE_SCAN,Environment.BEAN_PACKAGE_SCAN};

    private ProcessEngineType processEngineType;

    public DynamicBeanInitialize(ProcessEngineType processEngineType) {
        this.processEngineType = processEngineType;
    }

    @Override
    public Map<String, AbstractBeanDefinition> createBeanDefinitions() {

        Map<String, AbstractBeanDefinition> definitionMap = new HashMap<String, AbstractBeanDefinition>();

        for(String pkg : this.beanPackagesToScan){
            try{
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                        ClassUtils.convertClassNameToResourcePath(pkg) + BEAN_CLASS_RESOURCE_PATTERN;

                Resource[] resources = this.resourcePatternResolver.getResources(pattern);
                MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        MetadataReader reader = readerFactory.getMetadataReader(resource);
                        String className = reader.getClassMetadata().getClassName();
                        Class<?> cls = Class.forName(className);


                       DynamicBean annot =  cls.getAnnotation(DynamicBean.class);

                        if(annot!=null){

                            //如果声明的 流程引擎 串为空,说明跟流程无关则加载bean, 如果 声明流程引擎不为空,并且跟当前设置的不一致 说明不需要加载
                            if(!"".equals(annot.processEngineType())&&!annot.processEngineType().equals(processEngineType.getType())){
                                continue;
                            }


                            BeanDefinitionBuilder beanDefinitionBuilder = null;

                            if(!"".equals(annot.parent())){
                                beanDefinitionBuilder = childBeanDefinition(annot.parent());
                                beanDefinitionBuilder.getRawBeanDefinition().setBeanClass(cls);
                            }else{
                                beanDefinitionBuilder = genericBeanDefinition(className);
                            }

                            beanDefinitionBuilder.setAutowireMode(annot.autowireMode());

                            if(!"".equals(annot.factoryBeanName())){
                                beanDefinitionBuilder.getRawBeanDefinition().setFactoryBeanName(annot.factoryBeanName());
                            }
                            if(!"".equals(annot.factoryMethodName())){
                                beanDefinitionBuilder.getRawBeanDefinition().setFactoryMethodName(annot.factoryMethodName());
                            }
                            if(!"".equals(annot.dependsOn())){
                                beanDefinitionBuilder.addDependsOn(annot.dependsOn());
                            }
                            if(!"".equals(annot.initMethodName())){
                                beanDefinitionBuilder.setInitMethodName(annot.initMethodName());
                            }

                            beanDefinitionBuilder.setScope(annot.scope());


                            beanDefinitionBuilder.setLazyInit(annot.lazyInit());


                            beanDefinitionBuilder.setAbstract(annot.isAbstract());

                            beanDefinitionBuilder.setDependencyCheck(annot.dependencyCheck());


                            definitionMap.put(annot.beanName(),beanDefinitionBuilder.getBeanDefinition());

                            continue;
                        }

                    }
                }
            }catch (IOException e){
                throw new BusinessException("动态bean加载I/O出错!"+e.getMessage(),e);
            } catch (ClassNotFoundException e) {
                throw new BusinessException("动态bean类没有找到!"+e.getMessage(),e);
            }
        }


        return definitionMap;
    }




}
