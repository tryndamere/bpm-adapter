package org.bpm.engine.impl.activiti;

import org.activiti.engine.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.bpm.engine.impl.BaseServiceImpl;

/**
 * Created by rocky on 14-5-13.
 */
public abstract class ActivitiBaseService extends BaseServiceImpl{

    protected RuntimeService runtimeService ;

    protected TaskService taskService;

    protected IdentityService identityService;

    protected HistoryService historyService;

    protected RepositoryService repositoryService;

    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    protected Task taskInstanceByTaskId(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    /**
     * 任意流
     *
     * @param taskId
     *          当前环节实例ID
     * @param targetTaskDefKey
     *          目标环节定义的KEY
     */
    protected void change2TargetTaskWithoutTransition(String taskId, String targetTaskDefKey) {
        Task currentTask = taskInstanceByTaskId(taskId);
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(currentTask.getProcessDefinitionId());
        ActivityImpl activity = processDefinition.findActivity(currentTask.getTaskDefinitionKey());
        activity.getOutgoingTransitions().clear();
        activity.createOutgoingTransition().setDestination(processDefinition.findActivity(targetTaskDefKey));
    }
}
