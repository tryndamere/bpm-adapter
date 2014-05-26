package org.bpm.spring.impl;

import org.bpm.engine.BpmEngine;
import org.bpm.engine.definition.BpmDefinition;
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
    protected BpmDefinition bpmDefinition;

    public BpmEngineImpl(Configuration configuration){
        this.configuration = configuration;
        this.applicationContext = configuration.getApplicationContext();


        bpmRuntime = buildBpmBean("org.bpm.engine.runtime.BpmRuntime");
        bpmDefinition = buildBpmBean("org.bpm.engine.definition.BpmDefinition");

    }


    public <T> T buildBpmBean(String bpmBeanName) {
        T bean = (T) applicationContext.getBean(bpmBeanName);

        if(bean instanceof BaseServiceImpl){
            ((BaseServiceImpl)bean).setTransactionManager(configuration.getTransactionManager());
            return new ServiceInvocationHandler(bean).proxy();
        }

        return bean;
    }

    @Override
    public BpmRuntime getBpmRuntime() {
        return bpmRuntime;
    }

    public BpmDefinition getBpmDefinition() {
        return bpmDefinition;
    }


    public void close(){
        //TODO 销毁一些对象

    }
}
