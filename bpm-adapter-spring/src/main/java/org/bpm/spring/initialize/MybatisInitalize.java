package org.bpm.spring.initialize;

import org.bpm.spring.initialize.init.AbstractBeanDefinitionImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

/**
 * Created by rocky on 14-5-14.
 */
public class MybatisInitalize extends AbstractBeanDefinitionImpl {

    public MybatisInitalize(DefaultListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public Map<String, AbstractBeanDefinition> createBeanDefinitions() {

        Map<String, AbstractBeanDefinition> definitionMap = new HashMap<String, AbstractBeanDefinition>();


        BeanDefinitionBuilder sqlSessionFactoryBean = rootBeanDefinition(SqlSessionFactoryBean.class);
        sqlSessionFactoryBean.addPropertyValue("configLocation", "classpath:mybatis.xml");
        sqlSessionFactoryBean.addPropertyReference("dataSource" , beanFactory.getBeanNamesForType(DataSource.class)[0]);
        definitionMap.put("sqlSessionFactory", sqlSessionFactoryBean.getBeanDefinition());


        BeanDefinitionBuilder sqlSessionTemplate = rootBeanDefinition(SqlSessionTemplate.class);
        sqlSessionTemplate.addConstructorArgReference("sqlSessionFactory");
        sqlSessionTemplate.setScope("prototype");
        definitionMap.put("sqlSessionTemplate", sqlSessionTemplate.getBeanDefinition());

//        BeanDefinitionBuilder bpmBaseDao = rootBeanDefinition(BpmBaseDao.class);
//        bpmBaseDao.addPropertyReference("sqlSessionTemplate", "sqlSessionTemplate");
//        bpmBaseDao.setAbstract(true);
//        definitionMap.put("bpmBaseDao", bpmBaseDao.getBeanDefinition());

//        BeanDefinitionBuilder bpmProcessDefinition = BeanDefinitionBuilder.rootBeanDefinition(BpmProcessDefinition.class);
//        bpmProcessDefinition.getRawBeanDefinition().setParentName( beanFactory.getBeanNamesForType(BpmBaseDao.class)[0]);

//        BeanDefinitionBuilder bpmProcessDefinition = rootBeanDefinition(BpmProcessConfigMapper.class);
//        bpmProcessDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
//        definitionMap.put("bpmProcessConfigMapper", bpmProcessDefinition.getBeanDefinition());

//        ChildBeanDefinition  bpmProcessDefinition1 = new ChildBeanDefinition("bpmBaseDao" , BpmProcessConfigMapper.class , bpmBaseDao.getBeanDefinition().getConstructorArgumentValues() , null) ;
//        bpmProcessDefinition1.setParentName("bpmBaseDao");
//        bpmProcessDefinition1.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
//        beanFactory.registerBeanDefinition("bpmProcessDefinition" , bpmProcessDefinition1);

        return definitionMap;
     }

}
