package org.bpm;

import java.util.Map;

/**
 * 针对流程的操作类
 * Created by rocky on 14-5-4.
 */
public interface BpmProcessRuntime {

    /**
     * 开启流程
     * @param userId 起草人
     * @param processDefKey 流程定义的KEY
     * @param businessKey 业务主键ID
     * @param variables 启动时放入流程参数
     * @param tenantId 租户ID
     * @return taskId 任务ID
     */
    public String startProcess(String userId ,String processDefKey, String businessKey , Map<String, Object> variables, String tenantId);

    /**
     * 终止流程
     * @param userId 提交人
     * @param taskId 任务ID
     * @param variables 流程参数
     */
    public void endProcess(String userId , String taskId , Map<String,Object> variables);

    /**
     * 删除流程
     * @param userId 提交人
     * @param taskId 任务ID
     * @param reason 删除原因
     */
    public void deleteProcess(String userId , String taskId , String reason);

    /**
     * 更新业务主键
     * @param processInstanceId 流程实例ID
     * @param businessKey 业务主键
     */
    public void updateBusinessKey(String processInstanceId, String businessKey);

}
