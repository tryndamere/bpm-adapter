package org.bpm.spring.initialize;

import org.apache.ibatis.session.SqlSessionFactory;
import org.bpm.db.BpmBaseDao;
import org.bpm.db.config.BpmProcessConfigMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import javax.sql.DataSource;

/**
 * Created by rocky on 14-5-14.
 */
public class MybatisInitalize {

    private DefaultListableBeanFactory beanFactory;

    public MybatisInitalize(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void init(){
        BeanDefinitionBuilder sqlSessionFactoryBean = BeanDefinitionBuilder.rootBeanDefinition(SqlSessionFactoryBean.class);
        sqlSessionFactoryBean.addPropertyValue("configLocation", "classpath:mybatis.xml");
        sqlSessionFactoryBean.addPropertyReference("dataSource" , beanFactory.getBeanNamesForType(DataSource.class)[0]);
        beanFactory.registerBeanDefinition("sqlSessionFactoryBean" , sqlSessionFactoryBean.getBeanDefinition());

        BeanDefinitionBuilder sqlSessionFactory = BeanDefinitionBuilder.rootBeanDefinition(SqlSessionFactory.class);
        AbstractBeanDefinition rawBeanDefinition = sqlSessionFactory.getRawBeanDefinition();
        rawBeanDefinition.setFactoryBeanName("sqlSessionFactoryBean");
        rawBeanDefinition.setFactoryMethodName("getObject");
        beanFactory.registerBeanDefinition("sqlSessionFactory", sqlSessionFactory.getBeanDefinition());

        BeanDefinitionBuilder sqlSessionTemplate = BeanDefinitionBuilder.rootBeanDefinition(SqlSessionTemplate.class);
        sqlSessionTemplate.addConstructorArgReference(beanFactory.getBeanNamesForType(SqlSessionFactory.class)[0]);
        sqlSessionTemplate.setScope("prototype");
        beanFactory.registerBeanDefinition("sqlSessionTemplate" , sqlSessionTemplate.getBeanDefinition());

        BeanDefinitionBuilder bpmBaseDao = BeanDefinitionBuilder.rootBeanDefinition(BpmBaseDao.class);
        bpmBaseDao.addPropertyReference("sqlSessionTemplate", beanFactory.getBeanNamesForType(SqlSessionTemplate.class)[0]);
        bpmBaseDao.setAbstract(true);
        beanFactory.registerBeanDefinition("bpmBaseDao" , bpmBaseDao.getBeanDefinition());

//        BeanDefinitionBuilder bpmProcessDefinition = BeanDefinitionBuilder.rootBeanDefinition(BpmProcessDefinition.class);
//        bpmProcessDefinition.getRawBeanDefinition().setParentName( beanFactory.getBeanNamesForType(BpmBaseDao.class)[0]);

        BeanDefinitionBuilder bpmProcessDefinition = BeanDefinitionBuilder.rootBeanDefinition(BpmProcessConfigMapper.class);
        bpmProcessDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
        beanFactory.registerBeanDefinition("bpmProcessConfigMapper" , bpmProcessDefinition.getBeanDefinition());

//        ChildBeanDefinition  bpmProcessDefinition1 = new ChildBeanDefinition("bpmBaseDao" , BpmProcessConfigMapper.class , bpmBaseDao.getBeanDefinition().getConstructorArgumentValues() , null) ;
//        bpmProcessDefinition1.setParentName("bpmBaseDao");
//        bpmProcessDefinition1.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
//        beanFactory.registerBeanDefinition("bpmProcessDefinition" , bpmProcessDefinition1);
    }
}
