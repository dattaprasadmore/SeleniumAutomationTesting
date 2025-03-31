package com.ui.utils.exceptionUtil;

public class InvalidOperationFrameworkException extends FrameworkException{

    public InvalidOperationFrameworkException(String message) {
        super(message);
    }

    public InvalidOperationFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
