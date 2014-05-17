package org.bpm.spring.cfg.initialize;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

/**
 * Created by rocky on 14-5-14.
 */
public class MybatisInitalize extends AbstractBeanDefinitionImpl {

    private String dataSourceBeanName;
    private String transactionManagerBeanName;

    public MybatisInitalize( String dataSourceBeanName, String transactionManagerBeanName) {
        this.dataSourceBeanName = dataSourceBeanName;
        this.transactionManagerBeanName = transactionManagerBeanName;
    }

    @Override
    public Map<String, AbstractBeanDefinition> createBeanDefinitions() {

        Map<String, AbstractBeanDefinition> definitionMap = new HashMap<String, AbstractBeanDefinition>();


        BeanDefinitionBuilder sqlSessionFactoryBean = rootBeanDefinition(SqlSessionFactoryBean.class);
        sqlSessionFactoryBean.addPropertyValue("configLocation", "classpath:mybatis.xml");
        sqlSessionFactoryBean.addPropertyReference("dataSource" , dataSourceBeanName);
        definitionMap.put("sqlSessionFactory", sqlSessionFactoryBean.getBeanDefinition());


        BeanDefinitionBuilder sqlSessionTemplate = rootBeanDefinition(SqlSessionTemplate.class);
        sqlSessionTemplate.addConstructorArgReference("sqlSessionFactory");
        sqlSessionTemplate.setScope("prototype");
        definitionMap.put("sqlSessionTemplate", sqlSessionTemplate.getBeanDefinition());

        return definitionMap;
     }

}
