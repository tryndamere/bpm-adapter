package org.bpm.spring.impl;

import org.bpm.common.exception.impl.TransientDataAccessException;
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
 * Created by izerui.com on 14-5-13.
 */
public class ServiceInvocationHandler implements InvocationHandler{

    protected Object target;

    protected PlatformTransactionManager transactionManager;

    /**
     * @param target 要代理的对象
     * @param transactionManager 事务控制器
     */
    public ServiceInvocationHandler(Object target,PlatformTransactionManager transactionManager){
        this.target = target;
        this.transactionManager = transactionManager;
    }

    public <T> T proxy(){
        //返回代理对象
        T obj = (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
        return obj;
    }

    /**
     * 调用方法
     */
    @Override
     public Object invoke(Object proxy,final Method method,final Object[] args) throws Throwable {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_MANDATORY);

        Object result = transactionTemplate.execute(new TransactionCallback<Object>() {
            public Object doInTransaction(TransactionStatus status) {
                return execute(target,method,args);
            }
        });
        return result;
    }

    /**
     * 执行目标对象的方法
     * @param target 目标对象
     * @param method 方法
     * @param args 参数数组
     * @return 执行后的结果
     */
    public Object execute(Object target,Method method,Object[] args){
        Object result = null;
        try {
            result = method.invoke(target, args);

            if(target instanceof BaseServiceImpl){
                ((BaseServiceImpl) target).afterMethodInvoke(method,args);
            }

        } catch (IllegalAccessException e) {
            throw new TransientDataAccessException("事务内异常："+e.getMessage(),e);
        } catch (InvocationTargetException e) {
            throw new TransientDataAccessException("事务内异常："+e.getMessage(),e);
        }
        return result;
    }

}
