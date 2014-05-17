package org.bpm.spring.cfg;

import org.bpm.engine.BpmEngine;
import org.bpm.engine.Environment;
import org.bpm.spring.ProcessEngineType;
import org.bpm.spring.impl.BpmEngineImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.Serializable;

/**
 * Created by serv on 14-5-7.
 */
public class Configuration implements ApplicationContextAware,Serializable,Environment {

    private ApplicationContext applicationContext;

    private PlatformTransactionManager transactionManager;

    private DataSource dataSource;

    private ProcessEngineType processEngineType;

    /**
     * 创建流程引擎对象
     * @return
     */
    public BpmEngine buildBpmEngine() {
        return new BpmEngineImpl(this);
    }

    /**
     * 获取spring 上下文
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取事务管理器
     * @return
     */
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * 获取数据源
     * @return
     */
    public DataSource getDataSource() {
        return dataSource;
    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ProcessEngineType getProcessEngineType() {
        return processEngineType;
    }

    public void setProcessEngineType(ProcessEngineType processEngineType) {
        this.processEngineType = processEngineType;
    }
}
