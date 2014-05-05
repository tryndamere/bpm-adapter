package org.bpm.exception;

/**
 * 自定义运行时异常
 * Created by rocky on 14-5-4.
 */
public class BpmRuntimeException extends RuntimeException{

    public BpmRuntimeException(String msg) {
        super(msg);
    }

    public BpmRuntimeException(String msg , Throwable throwable) {
        super(msg,throwable);
    }

    public BpmRuntimeException(Throwable throwable) {
        super(throwable);
    }

}
