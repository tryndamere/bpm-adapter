package org.bpm;

import org.bpm.vo.BpmTask;

import java.io.InputStream;
import java.util.List;

/**
 * 流程跟踪
 * Created by rocky on 14-5-5.
 */
public interface BpmTrace {

    /**
     * 获取流程图
     * @param taskId 任务ID
     * @param processDefKey 流程定义的KEY
     * @return
     */
    public InputStream traceImage(String taskId , String processDefKey);

    /**
     * 为图形化流程跟踪服务
     * @param taskId 任务ID
     * @return
     */
    public List<BpmTask> traceText2Image(String taskId);


    /**
     * 文本跟踪
     * @param taskId 任务ID
     * @return
     */
    public List<BpmTask> traceText(String taskId);

}
