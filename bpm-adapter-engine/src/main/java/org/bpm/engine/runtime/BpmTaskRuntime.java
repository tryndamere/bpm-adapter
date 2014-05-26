package org.bpm.engine.runtime;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.bpm.engine.impl.activiti.vo.BpmTask;

import java.util.List;
import java.util.Map;

/**
 * 针对任务的操作类
 * Created by rocky on 14-5-4.
 */
public interface BpmTaskRuntime {

    /**
     * 完成任务
     * @param userId 用户ID
     * @param taskId 任务ID
     * @param variables 流程参数
     */
    public void completeTask(String userId , String taskId, Map<String,Object> variables);

    /**
     * 退回到指定环节
     * @param userId 提交人
     * @param taskId 任务ID
     * @param targetDefKey 目标环节定义的KEY
     * @param variables 流程参数
     */
    public void back2PointTask(String userId ,String taskId , String targetDefKey , Map<String,Object> variables);

    /**
     * 驳回到起草
     * @param userId 提交人
     * @param taskId 任务ID
     * @param variables 流程参数
     */
    public void rejectTask(String userId , String taskId , Map<String,Object> variables);

    /**
     * 获取下面将要执行的多个环节信息
     * @param userId 提交人
     * @param taskId 任务ID
     * @param variables 流程参数
     * @return 返回下面的多个环节的列表
     */
    public List<ActivityDefinition> nextActivities(String userId, String taskId, Map<String, Object> variables);

    /**
     * 签收任务，针对recieveTask
     * @param userId 提交人
     * @param taskId 任务ID
     * @param variables 流程参数
     */
    public void signalTask(String userId , String taskId , Map<String,Object> variables);

    /**
     * 签收任务
     * @param userId 提交人
     * @param taskId 任务ID
     */
    public void claimTask(String userId , String taskId);

    /**
     * 加签
     * @param userId 当前用户
     * @param taskId 当前任务ID
     * @param variables 流程参数
     */
    public void addSignTask(String userId , String taskId , Map<String,Object> variables);

    /**
     * 减签
     * @param taskId 任务ID
     * @param variables 流程参数
     */
    public void reduceSignTask(String userId , String taskId , Map<String,Object> variables);

    /**
     * 转办，将任务人员委托给他人办理
     * @param taskId 任务ID
     * @param delegatedUserId 被委托人
     */
    public void delegateAssignee(String userId , String taskId , String delegatedUserId);

}
