package org.bpm.common.exception;

/**
 * 数据一致异常
 */
@SuppressWarnings("serial")
public abstract class DataConsistencyException extends PlatformException {
    
    public DataConsistencyException(String errorCode, String errorDetail) {
        super(errorCode, errorDetail);
    }

    public DataConsistencyException(String errorCode, String errorDetail, Throwable cause) {
        super(errorCode, errorDetail, cause);
    }

}
