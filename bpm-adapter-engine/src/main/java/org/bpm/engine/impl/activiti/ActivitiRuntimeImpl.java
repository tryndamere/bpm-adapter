package org.bpm.engine.impl.activiti;

import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.bpm.common.stereotype.DynamicBean;
import org.bpm.db.config.BpmProcessConfigMapper;
import org.bpm.db.config.po.BpmProcessConfig;
import org.bpm.engine.Environment;
import org.bpm.engine.impl.activiti.listener.FollowActivityEventListener;
import org.bpm.engine.impl.activiti.vo.BpmActivity;
import org.bpm.engine.runtime.ActivityDefinition;
import org.bpm.engine.runtime.BpmRuntime;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rocky on 14-5-13.
 */
@DynamicBean(beanName = "org.bpm.engine.runtime.BpmRuntime",processEngineType = Environment.ACTIVITI_ENGINE_TYPE)
@Transactional(propagation = Propagation.MANDATORY)
public class ActivitiRuntimeImpl extends ActivitiBaseService implements BpmRuntime {

    private BpmProcessConfigMapper bpmProcessConfigMapper;

    public void setBpmProcessConfigMapper(BpmProcessConfigMapper bpmProcessConfigMapper) {
        this.bpmProcessConfigMapper = bpmProcessConfigMapper;
    }

    public void beforeMethodInvoke(Method method, Object[] args) {
//        identityService.setAuthenticatedUserId(args[0].toString());
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
        completeTask(userId, taskId, variables, false, false);
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
        back2PointTask(userId, taskId, startTaskDefKey, variables);
    }

    @Override
    public List<ActivityDefinition> nextActivities(String userId, final String taskId, final Map<String, Object> variables) {

        new TransactionTemplate(transactionManager).execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                FollowActivityEventListener activityEventListener = new FollowActivityEventListener();
                runtimeService.addEventListener(activityEventListener, ActivitiEventType.ACTIVITY_STARTED);
                taskService.complete(taskId,variables);
                runtimeService.removeEventListener(activityEventListener);
                status.setRollbackOnly();
                return null;
            }
        });

        return FollowActivityContext.getActivitys();
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


    @Override
    public void test() {
        BpmProcessConfig bpmProcessConfig = new BpmProcessConfig();
        bpmProcessConfig.setId("1000");
        bpmProcessConfig.setProcessDefKey("test");
        bpmProcessConfig.setTaskDefKey("test");
        bpmProcessConfig.setTaskConfig("xxxxxx");
        bpmProcessConfig.setTaskDefType(BpmProcessConfig.TASK_DEF_TYPE.START.getValue());
        bpmProcessConfigMapper.insert(bpmProcessConfig);
    }
}
