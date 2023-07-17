package org.F1Soft.testModules;

import org.F1Soft.Helpers.EmailHelper;
import org.F1Soft.PagePactory.Login;
import org.F1Soft.Setups.BaseSetups;
import org.F1Soft.listners.RetryAnalyzer;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;

public class LoginTest extends BaseSetups{


    @Test( description = "Data Driven Login Test", dataProviderClass = org.F1Soft.Setups.TestNGDataProvider.class, dataProvider = "loginTest")
    public void LoginTest(HashMap loginData){
        Login loginPage= new Login(driver);
        loginPage.login((String) loginData.get("USERNAME"),System.getProperty("password"),(String) loginData.get("SHOULDLOGIN"));

    }
}
