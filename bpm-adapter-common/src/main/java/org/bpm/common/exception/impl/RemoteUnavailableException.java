package org.bpm.common.exception.impl;

import org.bpm.common.exception.DataConsistencyException;

/**
 * 后台服务不可用异常
 * 
 * <pre>
 * 划分标准：
 *         1.连接后台失败
 * </pre>
 */
@SuppressWarnings("serial")
public class RemoteUnavailableException extends DataConsistencyException {

    /**
     * 后台服务不可用异常错误码
     */
    public static final String ERROR_CODE = "E999995";
    
    public RemoteUnavailableException(String errorDetail) {
        super(ERROR_CODE, errorDetail);
    }

    public RemoteUnavailableException(String errorDetail, Throwable cause) {
        super(ERROR_CODE, errorDetail, cause);
    }

}
