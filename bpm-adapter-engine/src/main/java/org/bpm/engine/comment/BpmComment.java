package org.bpm.engine.comment;

/**
 * 意见接口，此接口只是内部使用
 * Created by rocky on 14-5-6.
 */
public interface BpmComment {

    /**
     * 新增意见
     * @param userId 提交人
     * @param taskId 任务ID
     * @param processInstanceId 流程实例ID
     * @param comments 意见内容 可拼接成json或XML等格式串
     */
    public void addComment(String userId , String taskId , String processInstanceId , String comments);

    /**
     * 修改意见，更多是管理员使用的
     * @param userId 提交人
     * @param taskId 任务ID
     * @param comments 意见内容 可拼接成json或XML等格式串
     */
    public void updateComment(String userId , String taskId , String comments);

    /**
     * 删除意见
     * @param userId 提交人
     * @param taskId 任务ID
     */
    public void deleteComment(String userId , String taskId);

}
