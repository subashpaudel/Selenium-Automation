package org.F1Soft.Setups;

import org.F1Soft.Helpers.TestDataProvider;
import org.testng.annotations.DataProvider;
import java.util.HashMap;
import java.util.List;

public class TestNGDataProvider {

    TestDataProvider testDataProvider = new TestDataProvider();

    public TestNGDataProvider() throws Exception {
    }

    @DataProvider(name = "loginTest")
    public Object[][] provideTestDataForLogin(){
        List<HashMap> loginData = testDataProvider.getLoginData();
        return convertListToObjectArray(loginData);
    }


    private Object[][] convertListToObjectArray(List<HashMap> list){
        Object [][] objArray = new Object[list.size()][];

        for(int i=0;i< list.size();i++){
            objArray[i] = new Object[1];
            objArray[i][0] = list.get(i);
        }

        return objArray;
    }
}
