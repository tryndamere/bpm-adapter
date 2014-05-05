package org.bpm.common.exception.impl;

/**
 * 后台服务响应超时或执行状态未知
 * 
 * <pre>
 * 划分标准：
 *         1.正确连接后台服务器，且发送请求数据成功，但没有返回响应数据的情况，
 *           或在规定时间内没有返回响应数据的情况
 * </pre>
 */
@SuppressWarnings("serial")
public class RemoteTimeOutException extends RemoteDataInconsistencyException {

    /**
     * 后台服务响应超时或执行状态未知异常错误码
     */
    public static final String ERROR_CODE = "R999996";

    public RemoteTimeOutException(String errorDetail) {
        super(ERROR_CODE, errorDetail);
    }

    public RemoteTimeOutException(String errorDetail, Throwable cause) {
        super(ERROR_CODE, errorDetail, cause);
    }

}
