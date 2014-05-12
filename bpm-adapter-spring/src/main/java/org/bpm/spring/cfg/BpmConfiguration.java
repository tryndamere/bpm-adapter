package org.bpm.spring.cfg;

import org.bpm.engine.BpmEngine;
import org.bpm.spring.BpmEngineImpl;
import org.bpm.spring.EngineType;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.util.Properties;

/**
 * Created by serv on 14-5-7.
 */
public class BpmConfiguration implements Configuration,Serializable{

    private Properties properties;

    /**
     * 创建流程引擎
     */
    @Override
    public BpmEngine buildBpmEngine(ApplicationContext applicationContext) {
        return new BpmEngineImpl(this,applicationContext);
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
        if(typeName.equals(ACTIVITI_ENGINE_TYPE)){
            return EngineType.ACTIVITI;
        }
        if(typeName.equals(JBPM4_ENGINE_TYPE)){
            return EngineType.JBPM4;
        }
        if(typeName.equals(JBPM6_ENGINE_TYPE)){
            return EngineType.JBPM6;
        }
        return null;
    }

    @Override
    public Configuration addProperties(Properties properties) {
        this.getProperties().putAll(properties);
        return this;
    }
}
