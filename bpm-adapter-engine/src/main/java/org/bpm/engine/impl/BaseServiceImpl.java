package org.bpm.engine.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Method;

/**
 * Created by izerui.com on 14-5-13.
 */
public class BaseServiceImpl {

    protected PlatformTransactionManager transactionManager;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public void afterMethodInvoke(Method method, Object[] args){
        //overide and do some thing what you want to do!
        //这里可以记录一些操作历史
        log.info("调用了{}类的{}方法,参数为：{}",this.getClass().getName(),method.getName(),args);

    }

    public void beforeMethodInvoke(Method method, Object[] args) {
        log.info("开始调用{}类的{}方法,参数为：{}",this.getClass().getName(),method.getName(),args);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
