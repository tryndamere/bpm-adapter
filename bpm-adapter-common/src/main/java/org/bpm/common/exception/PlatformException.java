package org.bpm.common.exception;

/**
 * 平台异常
 */
@SuppressWarnings("serial")
public abstract class PlatformException extends RuntimeException {

    /**
     * 错误码
     */
    protected String errorCode;

    /**
     * 错误详情
     */
    protected String errorDetail;

    /**
     * 异常错误码
     */
    public static final String ERROR_CODE = "E999999";

    public PlatformException(String errorCode, String errorDetail) {
        super(errorDetail);
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
    }

    public PlatformException(String errorCode, String errorDetail, Throwable cause) {
        super(errorDetail, cause);
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

}
