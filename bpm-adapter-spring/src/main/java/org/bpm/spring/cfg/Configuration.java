package org.bpm.spring.cfg;

import org.bpm.spring.EngineType;
import org.springframework.context.ApplicationContext;

/**
 * Created by serv on 14-5-7.
 */
public interface Configuration {

    /**
     * 流程引擎实现类型
     * @return
     */
    public EngineType getEngineType();

    /**
     * application 上下文
     * @return
     */
    public ApplicationContext getApplicationContext();



}
