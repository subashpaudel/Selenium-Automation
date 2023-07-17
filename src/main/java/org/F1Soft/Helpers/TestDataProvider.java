package org.F1Soft.Helpers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TestDataProvider {
    public List<HashMap> allLoginData;

    public TestDataProvider() throws Exception {
        Excel excel= new Excel("src/test/resources/TestData/LoginTestData.xlsx");
        allLoginData = excel.getExcelValue("Sheet1");
    }


    public List<HashMap> getLoginData(){
        return allLoginData;

    }

    public List<String> getAllUserNames(){
        return allLoginData.stream().map(item->(String)item.get("USERNAME")).collect(Collectors.toList());
    }
}
