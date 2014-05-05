package org.bpm.common.exception.impl;

import org.bpm.common.exception.DataConsistencyException;

/**
 * 数据操作无事务的异常
 * 
 * <pre>
 * 划分标准：
 *         1.操作数据库产生非事务的异常
 * </pre>
 */
@SuppressWarnings("serial")
public class NonTransientDataAccessException extends DataConsistencyException {

    /**
     * 数据操作无事务的异常错误码
     */
    public static final String ERROR_CODE = "E999998";

    public NonTransientDataAccessException(String errorDetail) {
        super(ERROR_CODE, errorDetail);
    }

    public NonTransientDataAccessException(String errorDetail, Throwable cause) {
        super(ERROR_CODE, errorDetail, cause);
    }

}
