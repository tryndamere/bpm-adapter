package org.bpm;

import java.io.InputStream;

/**
 * 流程跟踪
 * Created by rocky on 14-5-5.
 */
public interface BpmTrace {

    /**
     * 获取流程图
     * @param taskId 任务ID
     * @return
     */
    public InputStream traceImage(String taskId);

    /**
     * 获取流程图
     * @param processDefKey 流程定义key
     * @return
     */
    public InputStream traceImage2Origin(String processDefKey);

}
