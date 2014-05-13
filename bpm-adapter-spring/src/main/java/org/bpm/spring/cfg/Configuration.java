package org.bpm.spring.cfg;

import org.bpm.engine.BpmEngine;
import org.bpm.spring.EngineType;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by serv on 14-5-7.
 */
public interface Configuration extends Environment{

    /**
     * 创建流程引擎对象
     * @return
     */
    public BpmEngine buildBpmEngine();

    /**
     * 获取spring 上下文
     * @return
     */
    public ApplicationContext getApplicationContext();


    /**
     * 获取事务管理器
     * @return
     */
    public PlatformTransactionManager getTransactionManager();

    /**
     * 获取数据源
     * @return
     */
    public DataSource getDataSource();

    /**
     * 获取当前的属性配置器
     * @return
     */
    public Properties getProperties();


    /**
     * 获取流程引擎的类型
     * @return
     */
    public EngineType getEngineType();

    /**
     * 添加属性
     * @param extraProperties
     * @return
     */
    public Configuration addProperties(Properties extraProperties);



}
