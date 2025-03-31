package com.ui.utils.exceptionUtil;

public class InvalidSelectorFrameworkException extends FrameworkException{

    public InvalidSelectorFrameworkException(String message) {
        super(message);
    }

    public InvalidSelectorFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}