package org.bpm.spring;

import org.bpm.common.exception.PlatformException;
import org.bpm.engine.BpmEngine;
import org.bpm.spring.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 引擎工厂,生成各种service
 * Created by serv on 14-5-7.
 */
public class BpmEngineFactory implements FactoryBean<BpmEngine>,InitializingBean, DisposableBean {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * bpm服务提供者
     */
    protected BpmEngine bpmEngine;



    /**
     * bpm 配置
     */
    protected Configuration configuration;


    public Configuration getConfiguration(){
        return this.configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        this.bpmEngine = buildBpmEngine();

    }


    @Override
    public void destroy() throws PlatformException {
        log.info("销毁bpmEngine引擎!");
        try {
            beforeBpmEngineDestruction();
        }
        finally {
            this.bpmEngine.close();
        }
    }

    /**
     * 工厂对象
     */
    @Override
    public BpmEngine getObject() {
        return this.bpmEngine;
    }

    @Override
    public Class<BpmEngine> getObjectType() {
        return BpmEngine.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    /**
     * 覆盖该方法来触发销毁的事件
     */
    protected void beforeBpmEngineDestruction() {
    }


    protected BpmEngine buildBpmEngine() throws Exception {

        if (this.configuration == null) {
           throw new RuntimeException("没有配置属性管理器");
        }

        return configuration.buildBpmEngine();

    }




}
