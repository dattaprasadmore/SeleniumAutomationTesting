package com.ui.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
    private static final Object lock = new Object();
    private static Logger logger;
    private LoggerUtility(){

    }

    public static Logger getLogger(Class<?> claszz){
        if(logger==null){
            synchronized (lock){
                if(logger == null){
                    logger = LogManager.getLogger(claszz);
                }
            }
        }
        return logger;
    }
}