package org.bpm.spring.cfg;

import org.bpm.engine.BpmEngine;
import org.bpm.spring.BpmEngineImpl;
import org.bpm.spring.EngineType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by serv on 14-5-7.
 */
public class BpmConfiguration implements Configuration,ApplicationContextAware{

    protected ApplicationContext applicationContext;

    /**
     * 流程引擎实现,默认以ACTIVITI实现
     */
    protected EngineType engineType = EngineType.ACTIVITI;

    /**
     * 可通过覆盖该方法,自定义服务提供者
     */
    public BpmEngine buildBpmEngine() {
        return new BpmEngineImpl(this);
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
