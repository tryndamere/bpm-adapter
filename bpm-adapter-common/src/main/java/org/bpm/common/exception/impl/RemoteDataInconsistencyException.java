package org.bpm.common.exception.impl;

import org.bpm.common.exception.DataInconsistencyException;

/**
 * 后台数据不一致
 */
@SuppressWarnings("serial")
public class RemoteDataInconsistencyException extends DataInconsistencyException {

    /**
     * 后台数据不一致
     */
    public static final String ERROR_CODE = "R999999";

    public RemoteDataInconsistencyException(String errorDetail) {
        this(ERROR_CODE, errorDetail);
    }

    public RemoteDataInconsistencyException(String errorDetail, Throwable cause) {
        this(ERROR_CODE, errorDetail, cause);
    }

    protected RemoteDataInconsistencyException(String errorCode, String errorDetail) {
        super(errorCode, errorDetail);
    }

    protected RemoteDataInconsistencyException(String errorCode, String errorDetail, Throwable cause) {
        super(errorCode, errorDetail, cause);
    }

}
