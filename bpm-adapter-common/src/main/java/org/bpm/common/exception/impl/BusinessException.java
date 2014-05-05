package org.bpm.common.exception.impl;

import org.bpm.common.exception.DataConsistencyException;

/**
 * 业务异常
 * 
 * <pre>
 * 划分标准：
 *         1.本地出现的和业务相关的异常，或者是业务中参数校验判断抛出的异常
 *         2.后台正确返回对象，返回非正确数据的情况下抛出的异常
 * </pre>
 */
@SuppressWarnings("serial")
public class BusinessException extends DataConsistencyException {
    
    /**
     * 业务异常的异常错误码
     */
    public static final String ERROR_CODE = "E000000";

    public BusinessException(String errorDetail, Throwable cause) {
        super(ERROR_CODE, errorDetail, cause);
    }

    public BusinessException(String errorDetail) {
        super(ERROR_CODE, errorDetail);
    }

    public BusinessException(String errorCode, String errorDetail, Throwable cause) {
        super(errorCode, errorDetail, cause);
    }

    public BusinessException(String errorCode, String errorDetail) {
        super(errorCode, errorDetail);
    }

}
