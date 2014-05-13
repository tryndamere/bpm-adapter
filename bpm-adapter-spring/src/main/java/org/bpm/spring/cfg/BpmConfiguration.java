package org.bpm.spring.cfg;

import org.bpm.common.exception.impl.InternalErrorException;
import org.bpm.engine.BpmEngine;
import org.bpm.spring.BpmEngineImpl;
import org.bpm.spring.EngineType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by serv on 14-5-7.
 */
public class BpmConfiguration implements ApplicationContextAware,Configuration,Serializable{

    private Properties properties;

    private ApplicationContext applicationContext;

    private PlatformTransactionManager transactionManager;

    private DataSource dataSource;

    /**
     * 创建流程引擎
     */
    @Override
    public BpmEngine buildBpmEngine() {
        return new BpmEngineImpl(this);
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String propertyName) {
        return properties.getProperty( propertyName );
    }

    public Properties getProperties() {
        if (this.properties == null) {
            this.properties = new Properties();
        }
        return this.properties;
    }

    @Override
    public EngineType getEngineType() {

        String engineType = getProperty(ENGINE_TYPE);

        return getType(engineType);
    }

    private EngineType getType(String typeName){

        if(typeName==null){
            throw new InternalErrorException("没有配置默认流程引擎");
        }

        if(typeName.equals(ACTIVITI_ENGINE_TYPE)){
            return EngineType.ACTIVITI;
        }
        if(typeName.equals(JBPM4_ENGINE_TYPE)){
            return EngineType.JBPM4;
        }
        if(typeName.equals(JBPM6_ENGINE_TYPE)){
            return EngineType.JBPM6;
        }
        throw new InternalErrorException("流程引擎配置参数出错");
    }

    @Override
    public Configuration addProperties(Properties properties) {
        this.getProperties().putAll(properties);
        return this;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
