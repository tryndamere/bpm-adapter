package org.bpm.spring;

import org.bpm.engine.runtime.BpmRuntime;
import org.bpm.spring.cfg.Configuration;
import org.bpm.spring.cfg.Environment;
import org.bpm.spring.impl.ActivitiRuntimeImpl;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by serv on 14-5-7.
 */
public enum EngineType implements ServiceCreator{

    ACTIVITI(){
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration,ApplicationContext applicationContext) {
            return executeAutowire(applicationContext,ActivitiRuntimeImpl.class);

        }
    },JBPM4{
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration,ApplicationContext applicationContext) {
            return null;
        }
    },JBPM6{
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration,ApplicationContext applicationContext) {
            return null;
        }
    };


    public <T extends BpmRuntime> T executeAutowire(ApplicationContext applicationContext,Class cls){
        return (T) applicationContext.getAutowireCapableBeanFactory()
                .createBean(cls, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
    }


}
interface ServiceCreator extends Environment{

    public BpmRuntime createBpmRuntime(Configuration configuration,ApplicationContext applicationContext);
}
