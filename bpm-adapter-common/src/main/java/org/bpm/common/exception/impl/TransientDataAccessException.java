package org.bpm.common.exception.impl;

import org.bpm.common.exception.DataInconsistencyException;

/**
 * 数据操作存在事务的异常
 * 
 * <pre>
 * 划分标准：
 *         1.操作数据库产生存在事务的异常
 *         2.文件/数据解析操作会导致数据不一致的问题的异常
 * </pre>
 */
@SuppressWarnings("serial")
public class TransientDataAccessException extends DataInconsistencyException {
    
    /**
     * 数据操作存在事务的异常错误码
     */
    public static final String ERROR_CODE = "R999997";

    public TransientDataAccessException(String errorDetail) {
        super(ERROR_CODE, errorDetail);
    }

    public TransientDataAccessException(String errorDetail, Throwable cause) {
        super(ERROR_CODE, errorDetail, cause);
    }
    
}
