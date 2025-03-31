package com.ui.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public class ResourceLoader {
    private static InputStream stream;

/*
    This utility is used for to read file. First it will check the classpath, if found, it is used else it will go for filesystem
 */

    public static InputStream getResources(String fileName) {
        Properties prop = new Properties();
        InputStream stream = null;
        try{
            stream  = ResourceLoader.class.getClassLoader().getResourceAsStream(fileName);
            System.out.println("FILE INPUT STREAM : " + stream);
            if(Objects.nonNull(stream)){
                return stream;
            }
            return Files.newInputStream(Path.of(fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return stream;
    }
}