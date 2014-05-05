package org.bpm.common.exception;

/**
 * 数据不一致异常
 */
@SuppressWarnings("serial")
public abstract class DataInconsistencyException extends PlatformException {
    
    public DataInconsistencyException(String errorCode, String errorDetail) {
        super(errorCode, errorDetail);
    }

    public DataInconsistencyException(String errorCode, String errorDetail, Throwable cause) {
        super(errorCode, errorDetail, cause);
    }

}
