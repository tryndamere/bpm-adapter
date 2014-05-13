package org.bpm.spring;

import org.bpm.engine.BpmEngine;
import org.bpm.engine.runtime.BpmRuntime;
import org.bpm.spring.cfg.Configuration;
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

        EngineType engineType = configuration.getEngineType();



        bpmRuntime = engineType.createBpmRuntime(configuration);


    }

    @Override
    public BpmRuntime getBpmRuntime() {
        return bpmRuntime;
    }

    public void close(){
        //TODO 销毁一些对象

    }
}
