package org.bpm.spring;

import org.bpm.common.exception.PlatformException;
import org.bpm.common.exception.impl.TransientDataAccessException;
import org.bpm.engine.runtime.BpmRuntime;
import org.bpm.spring.cfg.Configuration;
import org.bpm.spring.cfg.Environment;
import org.bpm.spring.impl.ActivitiRuntimeImpl;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by serv on 14-5-7.
 */
public enum EngineType implements ServiceCreator{

    ACTIVITI(){
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration) {
            BpmRuntime bean =  getSpringAutowireBean(configuration, ActivitiRuntimeImpl.class);

            FacadeProxy proxy = new FacadeProxy(configuration.getTransactionManager());

            return (BpmRuntime) proxy.bind(bean);
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


    public <T extends BpmRuntime> T getSpringAutowireBean(Configuration configuration, Class cls){
        T bean = (T) configuration.getApplicationContext().getAutowireCapableBeanFactory()
                .createBean(cls, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        return bean;
    }


    class FacadeProxy implements InvocationHandler {


        private Object target;

        protected PlatformTransactionManager transactionManager;

        public FacadeProxy(PlatformTransactionManager transactionManager){
            this.transactionManager = transactionManager;
        }

        /**
         * 绑定委托对象并返回一个代理类
         * @param target
         * @return
         */
        public Object bind(Object target) {
            this.target = target;
            //取得代理对象
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(), this);
        }

        /**
         * 调用方法
         */
        @Override
        public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {

            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_MANDATORY);

            Object result = transactionTemplate.execute(new TransactionCallback<Object>() {
                public Object doInTransaction(TransactionStatus status) {
                    try {
                        return method.invoke(target, args);
                    } catch (IllegalAccessException e) {
                        throw new TransientDataAccessException("事务内异常："+e.getMessage(),e);
                    } catch (InvocationTargetException e) {
                        throw new TransientDataAccessException("事务内异常："+e.getMessage(),e);
                    }
                }
            });
            return result;
        }

    }

}
interface ServiceCreator extends Environment{

    public BpmRuntime createBpmRuntime(Configuration configuration);
}
