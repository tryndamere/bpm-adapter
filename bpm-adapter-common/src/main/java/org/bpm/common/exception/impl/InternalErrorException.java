package org.bpm.common.exception.impl;

import org.bpm.common.exception.DataConsistencyException;

/**
 * 内部异常
 * 
 * <pre>
 * 划分标准：
 *         1.所有没有划归到其他部分的异常
 *         2.JDK自带异常的转义
 * </pre>
 */
@SuppressWarnings("serial")
public class InternalErrorException extends DataConsistencyException {

    /**
     * 内部异常的异常错误码
     */
    public static final String ERROR_CODE = "E999999";

    public InternalErrorException(String errorDetail) {
        this(ERROR_CODE, errorDetail);
    }

    public InternalErrorException(String errorDetail, Throwable cause) {
        this(ERROR_CODE, errorDetail, cause);
    }

    protected InternalErrorException(String errorCode, String errorDetail) {
        super(errorCode, errorDetail);
    }

    protected InternalErrorException(String errorCode, String errorDetail, Throwable cause) {
        super(errorCode, errorDetail, cause);
    }

}
