package org.bpm.engine.runtime;

import java.util.List;
import java.util.Map;

/**
 * 管理员维护功能点
 * Created by rocky on 14-5-6.
 */
public interface BpmAdminRuntime {

    /**
     * 任意流，管理员功能点
     * @param userId 提交人
     * @param taskId 任务ID
     * @param assignees 签收人
     * @param variables 流程参数
     */
    public void admin(String userId , String taskId , List<String> assignees , Map<String,Object> variables);
}
