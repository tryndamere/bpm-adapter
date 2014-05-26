package org.bpm.engine.impl.activiti;

import org.bpm.common.stereotype.DynamicBean;
import org.bpm.engine.Environment;
import org.bpm.engine.definition.BpmDefinition;
import org.bpm.engine.impl.activiti.vo.BpmProcess;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by izerui.com on 14-5-26.
 */
@DynamicBean(beanName = "org.bpm.engine.definition.BpmDefinition",processEngineType = Environment.ACTIVITI_ENGINE_TYPE)
@Transactional(propagation = Propagation.MANDATORY)
public class ActivitiDefinitionImpl extends ActivitiBaseService implements BpmDefinition{
    @Override
    public void deploy(String category, ZipInputStream zipInputStream, String tenantId) {
        repositoryService.createDeployment().category(category)
                .tenantId(tenantId).addZipInputStream(zipInputStream).deploy();
    }

    @Override
    public List<BpmProcess> allBpmProcesses(String tenantId) {
        return null;
    }

    @Override
    public void deleteProcessesByProcessDefKey(String processDefKey, boolean cascade) {

    }

    @Override
    public void deleteProcessByDeploymentId(String deploymentId, boolean cascade) {

    }
}
