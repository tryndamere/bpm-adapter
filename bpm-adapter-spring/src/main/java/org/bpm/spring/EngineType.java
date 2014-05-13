package org.bpm.spring;

import org.bpm.engine.runtime.BpmRuntime;
import org.bpm.spring.cfg.Configuration;
import org.bpm.spring.cfg.Environment;
import org.bpm.spring.impl.ActivitiRuntimeImpl;
import org.bpm.spring.impl.ServiceInvocationHandler;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * Created by serv on 14-5-7.
 */
public enum EngineType implements ServiceCreator{

    ACTIVITI(){
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration) {
            BpmRuntime bean =  getProxySpringAutowireBean(configuration, ActivitiRuntimeImpl.class);

            return bean;
        }
    },JBPM4{
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration) {
            return null;
        }
    },JBPM6{
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration) {
            return null;
        }
    };


    public <T> T getProxySpringAutowireBean(Configuration configuration, Class cls){
        T bean = (T) configuration.getApplicationContext().getAutowireCapableBeanFactory()
                .createBean(cls, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);

        return new ServiceInvocationHandler(bean,configuration).proxy();

    }

}
interface ServiceCreator extends Environment{

    public BpmRuntime createBpmRuntime(Configuration configuration);
}
