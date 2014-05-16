package org.bpm.engine.impl.activiti;

import org.activiti.engine.runtime.ProcessInstance;
import org.bpm.common.BeanName;
import org.bpm.common.stereotype.DynamicBean;
import org.bpm.db.config.BpmProcessConfigMapper;
import org.bpm.engine.Environment;
import org.bpm.engine.impl.activiti.vo.BpmTask;
import org.bpm.engine.runtime.BpmRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by rocky on 14-5-13.
 */
@DynamicBean(beanName = BeanName.BEAN_NAME_BPM_RUNTIME,processEngineType = Environment.ACTIVITI_ENGINE_TYPE)
@Transactional
public class ActivitiRuntimeImpl extends ActivitiBaseService implements BpmRuntime {
    @Autowired
    private BpmProcessConfigMapper bpmProcessConfigMapper;

    public void beforeMethodInvoke(Method method, Object[] args) {
        identityService.setAuthenticatedUserId(args[0].toString());
        log.info("开始调用{}类的{}方法,参数为：{}",this.getClass().getName(),method.getName(),args);
    }

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
        String processDefinitionKey = processDefinitionByTaskId(taskId).getKey();
        String endTaskDefKey = bpmProcessConfigMapper.endTaskDefkey(processDefinitionKey).getTaskDefKey();
        change2TargetTaskWithoutTransition(taskId,endTaskDefKey);
        completeTask(userId , taskId , variables);
        //TODO 当前流程状态表
    }

    @Override
    public void deleteProcess(String userId, String taskId, String reason) {
        runtimeService.deleteProcessInstance(historicTaskInstanceByTaskId(taskId).getProcessInstanceId() , reason);
    }

    @Override
    public void updateBusinessKey(String userId ,String taskId, String businessKey) {
        runtimeService.updateBusinessKey(historicTaskInstanceByTaskId(taskId).getProcessInstanceId(), businessKey);
    }

    @Override
    public void completeTask(String userId, String taskId, Map<String, Object> variables) {
        completeTask(userId,taskId,variables,false,false);
    }

    public void completeTask(String userId , String taskId , Map<String, Object> variables , boolean isBack , boolean isEnd) {

    }

    @Override
    public void back2PointTask(String userId, String taskId, String targetDefKey, Map<String, Object> variables) {
        change2TargetTaskWithoutTransition(taskId,targetDefKey);
        String assignee = lastestTaskByTaskDefKeyAndTaskId(taskId , targetDefKey).getAssignee();
        completeTask(userId , taskId , variables);
    }

    @Override
    public void rejectTask(String userId, String taskId, Map<String, Object> variables) {
        String processDefinitionKey = processDefinitionByTaskId(taskId).getKey();
        String startTaskDefKey = bpmProcessConfigMapper.startTaskDefkey(processDefinitionKey).getTaskDefKey();
        back2PointTask(userId,taskId,startTaskDefKey,variables);
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
        taskService.claim(taskId , userId);
    }

    @Override
    public void addSignTask(String userId ,String taskId, Map<String, Object> variables) {

    }

    @Override
    public void reduceSignTask(String userId ,String taskId, Map<String, Object> variables) {

    }

    @Override
    public void delegateAssignee(String userId ,String taskId, String delegatedUserId) {
        taskService.delegateTask(taskId,delegatedUserId);
    }
}
