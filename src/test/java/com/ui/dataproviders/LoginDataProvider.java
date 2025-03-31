package com.ui.dataproviders;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.ui.utils.CSVReaderUtil;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {
    private static ThreadLocal<InputStream> threadLocalInputStream = ThreadLocal.withInitial(() -> null);
    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir")+"\\testData\\logindata.json");
        FileReader fileReader = new FileReader(testDataFile);
        TestData data = gson.fromJson(fileReader, TestData.class);

        List<Object[]> dataToReturn  = new ArrayList<Object[]>();

        for(User user : data.getData()){
            dataToReturn.add(new Object[]{user});
        }
        return dataToReturn.iterator();
    }
    //public static void main(String args[]) {
    @DataProvider(name = "LoginTestCSVDataProvider")
    public Iterator<User> loginCSVDataProvider() {
        List<User> users = null;
        try {
            CSVReaderUtil<User> reader = new CSVReaderUtil<>("testData/logindata.csv", User.class);
            users = reader.readCSV();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users.iterator();
    }

    //@DataProvider(name = "LoginTestExcelDataProvider")
    /*public Iterator<User> loginExcelDataProvider(){
        //return ExcelReaderUtility.readExcelFile("LoginData.xlsx");
    }*/
}