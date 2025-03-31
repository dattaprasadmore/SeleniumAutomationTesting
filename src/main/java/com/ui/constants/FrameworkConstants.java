package com.ui.constants;

import com.ui.utils.ResourceLoader;
import com.ui.enums.ConfigProperties;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.ui.utils.PropertyUtils;

public class FrameworkConstants {
    private FrameworkConstants() {}
    private static final String EXTENT_REPORT_FILE_PATH = System.getProperty("user.dir") + "\\ExtentReports\\";
    private static String extentReportFilePath = "";
    public static String getExtentReportFilePath(){
        if(extentReportFilePath.isEmpty()){
            extentReportFilePath =createReportPath();
        }
        return extentReportFilePath;
    }
    public static String createReportPath(){
        if(PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_mm_ss");
            LocalDateTime localDateTime = LocalDateTime.now();
            String formattedTime = dateTimeFormatter.format(localDateTime);
            return EXTENT_REPORT_FILE_PATH + formattedTime + "_Report" +".html";
        }else{
            return EXTENT_REPORT_FILE_PATH + "index.html";
        }
    }
}