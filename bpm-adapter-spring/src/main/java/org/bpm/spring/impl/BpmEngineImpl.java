package org.bpm.spring.impl;

import org.bpm.common.BeanName;
import org.bpm.engine.BpmEngine;
import org.bpm.engine.impl.BaseServiceImpl;
import org.bpm.engine.runtime.BpmRuntime;
import org.bpm.spring.cfg.Configuration;
import org.bpm.spring.impl.proxy.ServiceInvocationHandler;
import org.springframework.context.ApplicationContext;

/**
 * Created by serv on 14-5-7.
 */
public class BpmEngineImpl implements BpmEngine{

    protected Configuration configuration;
    protected ApplicationContext applicationContext;
    protected BpmRuntime bpmRuntime;

    public BpmEngineImpl(Configuration configuration){
        this.configuration = configuration;
        this.applicationContext = configuration.getApplicationContext();


        bpmRuntime = createBpmRuntime(configuration);


    }

    @Override
    public BpmRuntime getBpmRuntime() {
        return bpmRuntime;
    }

    public BpmRuntime createBpmRuntime(Configuration configuration) {
        BpmRuntime bean = (BpmRuntime) configuration.getApplicationContext().getBean(BeanName.BEAN_NAME_BPM_RUNTIME);

        if(bean instanceof BaseServiceImpl){
            return new ServiceInvocationHandler(bean,configuration).proxy();
        }
        return bean;
    }

    public void close(){
        //TODO 销毁一些对象

    }
}
