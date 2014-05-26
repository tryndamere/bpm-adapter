package org.bpm.engine.impl.jbpm6;

import org.bpm.common.stereotype.DynamicBean;
import org.bpm.common.Environment;
import org.bpm.engine.runtime.ActivityDefinition;
import org.bpm.engine.runtime.BpmRuntime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by rocky on 14-5-14.
 */
@DynamicBean(beanName = Environment.BEAN_BPMRUNTIME,processEngineType = Environment.JBPM6_ENGINE_TYPE)
@Transactional(propagation = Propagation.MANDATORY)
public class Jbpm6RuntimeImpl implements BpmRuntime {

    @Override
    public void admin(String userId, String taskId, List<String> assignees, Map<String, Object> variables) {

    }

    @Override
    public String startProcess(String userId, String processDefKey, String businessKey, Map<String, Object> variables, String tenantId) {
        return null;
    }

    @Override
    public void endProcess(String userId, String taskId, Map<String, Object> variables) {

    }

    @Override
    public void deleteProcess(String userId, String taskId, String reason) {

    }

    @Override
    public void updateBusinessKey(String userId, String processInstanceId, String businessKey) {

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
    public List<ActivityDefinition> nextActivities(String userId, String taskId, Map<String, Object> variables) {
        return null;
    }

    @Override
    public void signalTask(String userId, String taskId, Map<String, Object> variables) {

    }

    @Override
    public void claimTask(String userId, String taskId) {

    }

    @Override
    public void addSignTask(String userId, String taskId, Map<String, Object> variables) {

    }

    @Override
    public void reduceSignTask(String userId, String taskId, Map<String, Object> variables) {

    }

    @Override
    public void delegateAssignee(String userId, String taskId, String delegatedUserId) {

    }

}
