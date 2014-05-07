package org.bpm.spring;

import org.bpm.engine.BpmEngine;
import org.bpm.spring.cfg.BpmConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by serv on 14-5-7.
 */
public class BpmEngineFactory implements FactoryBean<BpmEngine>,DisposableBean,ApplicationContextAware {


    protected ApplicationContext applicationContext;
    /**
     * 服务提供者
     */
    protected BpmEngineImpl bpmEngine;

    /**
     * bpm 配置
     */
    protected BpmConfiguration configuration;


    /**
     * bpm服务提供者(工厂对象)
     */
    @Override
    public BpmEngine getObject() throws Exception {
        bpmEngine = (BpmEngineImpl) configuration.buildBpmEngine();
        return bpmEngine;
    }

    @Override
    public void destroy() throws Exception {
        if (bpmEngine != null) {
            bpmEngine.close();
        }
    }

    public BpmConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * 注入bpm配置信息
     */
    public void setConfiguration(BpmConfiguration configuration) {
        this.configuration = configuration;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public Class<BpmEngine> getObjectType() {
        return BpmEngine.class;
    }


    @Override
    public boolean isSingleton() {
        return true;
    }
}
