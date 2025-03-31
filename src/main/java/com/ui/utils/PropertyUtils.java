package com.ui.utils;

import com.ui.constants.FrameworkConstants;
import com.ui.enums.ConfigProperties;

import java.io.*;
import java.util.*;

public final class PropertyUtils {
    private PropertyUtils() {}

    // ThreadLocal to hold Properties for each thread
    private static ThreadLocal<Properties> threadLocalProp = ThreadLocal.withInitial(Properties::new);
    private static ThreadLocal<InputStream> threadLocalInputStream = ThreadLocal.withInitial(() -> null);
    public static Properties initProp(String envName ) {
        Properties prop = threadLocalProp.get(); // Get the Properties for the current thread

        if (prop.isEmpty()) { // Load properties only if they are not already loaded

            System.out.println("Running with : " + envName + " Environment");

            try {
                if (envName.equals("null")) {
                    System.out.println("Environment is not given. Test is running on the Default Environment");
                    threadLocalInputStream.set(ResourceLoader.getResources("config/config.properties"));
                } else {
                    switch (envName.trim()) {
                        case "dit":
                            threadLocalInputStream.set(ResourceLoader.getResources("config/dit.properties"));
                            break;
                        case "dev":
                            threadLocalInputStream.set(ResourceLoader.getResources("config/dev.properties"));
                            break;
                        case "fit":
                            threadLocalInputStream.set(ResourceLoader.getResources("config/fit.properties"));
                            break;
                        case "iat":
                            threadLocalInputStream.set(ResourceLoader.getResources("config/iat.properties"));
                            break;
                        case "uat":
                            threadLocalInputStream.set(ResourceLoader.getResources("config/uat.properties"));
                            break;
                        case "prod":
                            //threadLocalInputStream.set(FrameworkConstants.PROD_CONFIG_FILE_PATH);
                            threadLocalInputStream.set(ResourceLoader.getResources("config/prod.properties"));
                            break;
                        default:
                            System.out.println("No Env Found");
                            System.exit(0);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                if(threadLocalInputStream.get()!=null) {
                    prop.load(threadLocalInputStream.get());
                }
                threadLocalProp.set(prop);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return getProperties();
    }

    public static String get(ConfigProperties key) {
        return threadLocalProp.get().getProperty(key.name().toLowerCase());
    }

    public static Properties getProperties(){
        return threadLocalProp.get();
    }
}