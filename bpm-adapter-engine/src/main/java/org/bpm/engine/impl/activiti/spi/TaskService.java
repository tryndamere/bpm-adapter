package org.bpm.engine.impl.activiti.spi;

import org.bpm.common.stereotype.DynamicBean;
import org.bpm.common.Environment;

/**
 * Created by serv on 14-5-15.
 */
@Deprecated
@DynamicBean(beanName = "taskService",factoryBeanName = "processEngine",factoryMethodName = "getTaskService",processEngineType = Environment.ACTIVITI_ENGINE_TYPE)
public interface TaskService extends org.activiti.engine.TaskService{

}
