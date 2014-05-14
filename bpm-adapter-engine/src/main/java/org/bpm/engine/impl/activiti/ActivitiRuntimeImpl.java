package org.bpm.engine.impl.activiti;

import org.activiti.engine.runtime.ProcessInstance;
import org.bpm.engine.impl.activiti.vo.BpmTask;
import org.bpm.engine.runtime.BpmRuntime;

import java.util.List;
import java.util.Map;

/**
 * Created by rocky on 14-5-13.
 */
public class ActivitiRuntimeImpl extends ActivitiBaseService implements BpmRuntime {
    @Override
    public void admin(String userId, String taskId, List<String> assignees, Map<String, Object> variables) {

    }

    @Override
    public String startProcess(String userId, String processDefKey, String businessKey, Map<String, Object> variables, String tenantId) {
        identityService.setAuthenticatedUserId(userId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKeyAndTenantId(processDefKey, businessKey, variables, tenantId);
        return taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult().getId();
    }

    @Override
    public void endProcess(String userId, String taskId, Map<String, Object> variables) {

    }

    @Override
    public void deleteProcess(String userId, String taskId, String reason) {

    }

    @Override
    public void updateBusinessKey(String processInstanceId, String businessKey) {

    }

    @Override
    public void completeTask(String userId, String taskId, Map<String, Object> variables) {

    }

    @Override
    public void back2PointTask(String userId, String taskId, String targetDefKey, Map<String, Object> variables) {

    }

    @Override
    public void rejectTask(String userId, String taskId, Map<String, Object> variables) {

    }

    @Override
    public List<BpmTask> nextTaskInfos(String userId, String taskId, Map<String, Object> variables) {
        return null;
    }

    @Override
    public void signalTask(String userId, String taskId, Map<String, Object> variables) {

    }

    @Override
    public void claimTask(String userId, String taskId) {

    }

    @Override
    public void addSignTask(String taskId, Map<String, Object> variables) {

    }

    @Override
    public void reduceSignTask(String taskId, Map<String, Object> variables) {

    }

    @Override
    public void delegateAssignee(String taskId, String delegatedUserId) {

    }
}
