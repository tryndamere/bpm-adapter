package org.bpm.engine.impl.activiti.spi;

import org.bpm.common.stereotype.DynamicBean;
import org.bpm.engine.Environment;

/**
 * Created by serv on 14-5-15.
 */
@Deprecated
@DynamicBean(beanName = "formService",factoryBeanName = "processEngine",factoryMethodName = "getFormService",processEngineType = Environment.ACTIVITI_ENGINE_TYPE)
public interface FormService extends org.activiti.engine.FormService {
}
