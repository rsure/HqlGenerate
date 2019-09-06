package com.wangsh.hqlGenerate.exception;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public class HQLGenException extends RuntimeException {

    public HQLGenException() {
        super();
    }

    public HQLGenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HQLGenException(String message, Throwable cause) {
        super(message, cause);
    }

    public HQLGenException(String message) {
        super(message);
    }

    public HQLGenException(Throwable cause) {
        super(cause);
    }

}
