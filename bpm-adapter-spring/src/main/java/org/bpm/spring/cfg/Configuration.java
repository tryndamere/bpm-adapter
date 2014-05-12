package org.bpm.spring.cfg;

import org.bpm.engine.BpmEngine;
import org.bpm.spring.EngineType;
import org.springframework.context.ApplicationContext;

import java.util.Properties;

/**
 * Created by serv on 14-5-7.
 */
public interface Configuration extends Environment{

    /**
     * 流程引擎实现类型
     * @return
     */
    public BpmEngine buildBpmEngine(ApplicationContext applicationContext);


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
