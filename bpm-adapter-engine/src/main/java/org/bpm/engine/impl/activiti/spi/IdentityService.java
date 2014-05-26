package org.bpm.engine.impl.activiti.spi;

import org.bpm.common.stereotype.DynamicBean;
import org.bpm.common.Environment;

/**
 * Created by serv on 14-5-15.
 */
@Deprecated
@DynamicBean(beanName = "identityService",factoryBeanName = "processEngine",factoryMethodName = "getIdentityService",processEngineType = Environment.ACTIVITI_ENGINE_TYPE)
public interface IdentityService extends org.activiti.engine.IdentityService {
}
