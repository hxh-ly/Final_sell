package com.dgut.ssm.exception;

public class BalanceException  extends  RuntimeException{

    public BalanceException() {
    }

    public BalanceException(String message) {
        super(message);
    }

    public BalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BalanceException(Throwable cause) {
        super(cause);
    }

    public BalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
