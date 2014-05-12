package org.bpm.spring.impl;

import org.bpm.engine.impl.vo.BpmTask;
import org.bpm.engine.runtime.BpmRuntime;

import java.util.List;
import java.util.Map;

/**
 * Created by serv on 14-5-7.
 */
public class Jbpm4RuntimeImpl implements BpmRuntime{
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