package org.bpm.engine.impl.activiti.spi;

import org.bpm.common.stereotype.DynamicBean;
import org.bpm.common.Environment;

/**
 * Created by serv on 14-5-15.
 */
@Deprecated
@DynamicBean(beanName = "runtimeService",factoryBeanName = "processEngine",factoryMethodName = "getRuntimeService",processEngineType = Environment.ACTIVITI_ENGINE_TYPE)
public interface RuntimeService extends org.activiti.engine.RuntimeService {
}
