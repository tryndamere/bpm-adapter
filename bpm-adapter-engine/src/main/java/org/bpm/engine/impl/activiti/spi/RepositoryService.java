package org.bpm.engine.impl.activiti.spi;

import org.bpm.common.stereotype.DynamicBean;
import org.bpm.common.Environment;

/**
 * Created by serv on 14-5-15.
 */
@Deprecated
@DynamicBean(beanName = "repositoryService",factoryBeanName = "processEngine",factoryMethodName = "getRepositoryService",processEngineType = Environment.ACTIVITI_ENGINE_TYPE)
public interface RepositoryService extends org.activiti.engine.RepositoryService {
}
