package org.bpm.common.exception.impl;

/**
 * 方法不支持异常
 */
@SuppressWarnings("serial")
public class MethodNotSupportException extends InternalErrorException {

    /**
     * 方法不支持异常的异常错误码
     */
    public static final String ERROR_CODE = "E999991";

    public MethodNotSupportException(String errorDetail) {
        super(ERROR_CODE, errorDetail);
    }

    public MethodNotSupportException(String errorDetail, Throwable cause) {
        super(ERROR_CODE, errorDetail, cause);
    }

}
