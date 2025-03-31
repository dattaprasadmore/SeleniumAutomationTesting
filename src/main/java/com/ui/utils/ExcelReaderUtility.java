package com.ui.utils;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {

   /* public static Iterator<User> readExcelFile(String fileName) {
        File xlsxFile = new File(System.getProperty("user.dir")+ "\\testData\\"+fileName);
        XSSFWorkbook xssfWorkbook = null;
        List<User> userList = null;
        XSSFSheet xssfSheet;
        Row row;
        Cell emailAddress;
        Cell password;
        User user;
        Iterator<Row> rowIterator;
        try {
            xssfWorkbook = new XSSFWorkbook(xlsxFile);
            userList = new ArrayList<>();
            xssfSheet = xssfWorkbook.getSheet("LoginTestData");
            rowIterator = xssfSheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                emailAddress = row.getCell(0);
                password = row.getCell(1);
                user = new User(emailAddress.toString(), password.toString());
                userList.add(user);
            }
            xssfWorkbook.close();

        } catch (FileNotFoundException e) {
            throw new InvalidPathForFilesException("Excel File you trying to read is not found");
        } catch (IOException e) {
            throw new FrameworkException("Some IO Exception happened  while reading excel file");
        } catch (InvalidFormatException e){
            e.printStackTrace();
        } catch (InvalidOperationException e){
            throw new InvalidOperationFrameworkException("Some Invalid Operation Performed while Reading or Loading Excel file, Please check Exce Data file");
        }

        return userList.iterator();
    }*/
}