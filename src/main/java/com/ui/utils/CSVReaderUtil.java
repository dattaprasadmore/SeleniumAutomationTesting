package com.ui.utils;


import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReaderUtil<T>{

    private final String filePath;
    private final Class<T> targetClass;
    private final String delimiter;

    public CSVReaderUtil(String filePath, Class<T> targetClass, String delimiter) {
        this.filePath = filePath;
        this.targetClass = targetClass;
        this.delimiter = delimiter;
    }
    public CSVReaderUtil(String filePath, Class<T> targetClass) {
        this(filePath, targetClass, ","); // Default delimiter is comma
    }

    public List<T> readCSV() throws IOException, ReflectiveOperationException {
        List<T> objects = new ArrayList<>();
        InputStreamReader inputStream = new InputStreamReader(ResourceLoader.getResources(filePath), StandardCharsets.UTF_8);
            try (BufferedReader br = new BufferedReader(inputStream)) {
            String headerLine = br.readLine(); // Read the header line
            if (headerLine == null || headerLine.trim().isEmpty()) {
                System.out.println("CSV file is empty or has no header.");
                return objects;  // Return empty list if no data
            }

            String[] headers = headerLine.split(delimiter);
            headers = Arrays.stream(headers)
                    .map(String::trim)
                    .toArray(String[]::new); // Trim header names to avoid spaces causing issues

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                T object = createObjectFromCSV(headers, values);
                if(object != null) {
                    objects.add(object);
                }

            }
        }

        return objects;
    }

    private T createObjectFromCSV(String[] headers, String[] values) throws ReflectiveOperationException {
        if (headers.length != values.length) {
            System.err.println("Number of headers and values do not match. Skipping line.");
            return null;
        }

        T object = null;
        try {
            // Try to create an instance using the default constructor (if it exists)
            Constructor<T> constructor = targetClass.getDeclaredConstructor();
            constructor.setAccessible(true); //Allow access to private constructors
            object = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            //If default constructor doesn't exist, try to set fields directly.
            try{
                object = targetClass.getDeclaredConstructor().newInstance();
            }catch(NoSuchMethodException ex){
                System.err.println("No default constructor found for " + targetClass.getName() + " and no other constructors were used.");
                return null;
            }
            catch(Exception ex){
                System.err.println("Error creating instance using the constructor: " + ex.getMessage());
                return null;

            }
        }
        catch(Exception e){
            System.err.println("Error creating instance using the constructor: " + e.getMessage());
            return null;

        }


        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            String value = values[i].trim(); // Trim values to remove extra spaces

            try {
                Field field = targetClass.getDeclaredField(header);
                field.setAccessible(true);

                // Type conversion (basic)
                Object convertedValue = convertValue(value, field.getType());
                field.set(object, convertedValue);

            } catch (NoSuchFieldException e) {
                System.err.println("Field '" + header + "' not found in class " + targetClass.getName() + ". Skipping.");
            } catch (IllegalAccessException e) {
                System.err.println("Could not access field '" + header + "' in class " + targetClass.getName() + ". Skipping.");
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid argument type for field '" + header + "' in class " + targetClass.getName() + ". Value: " + value + ", Expected Type: " + targetClass.getDeclaredField(header).getType().getName() + ". Skipping.");
            }
        }

        return object;
    }


    private Object convertValue(String value, Class<?> targetType) {
        if (targetType == String.class) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return 0; // Default value or handle error appropriately
            }
        } else if (targetType == double.class || targetType == Double.class) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return 0.0; // Default value or handle error appropriately
            }
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        // Add more type conversions as needed

        return value; // Default: treat as String
    }

}