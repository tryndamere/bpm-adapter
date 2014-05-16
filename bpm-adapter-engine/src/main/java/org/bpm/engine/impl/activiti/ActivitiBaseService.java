package org.bpm.engine.impl.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.bpm.engine.impl.BaseServiceImpl;

import java.util.List;

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

    protected ProcessDefinition processDefinitionByTaskId(String taskId){
        String processDefinitonId = taskInstanceByTaskId(taskId).getProcessDefinitionId();
        return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitonId).singleResult();
    }

    protected HistoricTaskInstance historicTaskInstanceByTaskId(String taskId) {
        return historyService.createHistoricTaskInstanceQuery().taskId(taskId).includeProcessVariables()
                .includeTaskLocalVariables().singleResult();
    }

    protected HistoricTaskInstance lastestTaskByTaskDefKeyAndTaskId(String taskId , String taskDefKey) {
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().processInstanceId(taskInstanceByTaskId(taskId).getProcessInstanceId())
                .taskDefinitionKey(taskDefKey).orderByHistoricTaskInstanceStartTime().desc().list();
        if (historicTaskInstanceList != null && historicTaskInstanceList.size() >0) {
            return historicTaskInstanceList.get(0);
        }
        throw new NullPointerException("节点未流转过，无法回退！");
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
