package com.mds.passkit.exception;

public class PasskitException extends  Exception {

    public PasskitException() {
    }

    public PasskitException(String message) {
        super(message);
    }

    public PasskitException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasskitException(Throwable cause) {
        super(cause);
    }

    public PasskitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//    	 super(message, cause, enableSuppression, writableStackTrace);
    }
}
