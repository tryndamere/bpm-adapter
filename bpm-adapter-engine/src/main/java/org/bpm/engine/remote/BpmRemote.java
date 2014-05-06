package org.bpm.engine.remote;

import java.util.Map;

/**
 * 调用业务接口，需要业务实现
 * Created by rocky on 14-5-4.
 */
public interface BpmRemote {

    /**
     * 调用业务接口
     * @param variableMap 参数
     */
    public void remote2Client(Map<String, Object> variableMap);
}
