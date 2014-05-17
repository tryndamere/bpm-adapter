package org.bpm.spring.impl.proxy;

import org.bpm.engine.impl.BaseServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by izerui.com on 14-5-13.
 */
public class ServiceInvocationHandler implements InvocationHandler{

    protected Object target;

    /**
     * @param target 要代理的对象
     */
    public ServiceInvocationHandler(Object target){
        this.target = target;
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
        return execute(target,method,args);
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

            if(target instanceof BaseServiceImpl){
                ((BaseServiceImpl) target).beforeMethodInvoke(method, args);
            }
            result = method.invoke(target, args);

            if(target instanceof BaseServiceImpl){
                ((BaseServiceImpl) target).afterMethodInvoke(method,args);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return result;
    }

}
