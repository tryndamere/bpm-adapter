package org.bpm.spring;

import org.bpm.engine.BpmEngine;
import org.bpm.engine.runtime.BpmRuntime;
import org.bpm.spring.cfg.Configuration;

/**
 * Created by serv on 14-5-7.
 */
public class BpmEngineImpl implements BpmEngine{

    protected Configuration configuration;
    protected BpmRuntime bpmRuntime;

    public BpmEngineImpl(Configuration configuration){
        this.configuration = configuration;
        bpmRuntime = configuration.getEngineType().createBpmRuntime(configuration);

    }

    @Override
    public BpmRuntime getBpmRuntime() {
        return bpmRuntime;
    }

    public void close(){
        //TODO 销毁一些对象

    }
}
