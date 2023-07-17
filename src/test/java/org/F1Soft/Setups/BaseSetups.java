package org.F1Soft.Setups;

import org.F1Soft.Helpers.EmailHelper;
import org.F1Soft.Helpers.Environment;
import org.F1Soft.Helpers.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;


public class BaseSetups {
    public WebDriver driver;


    @BeforeTest(groups = {"Web"},alwaysRun = true)
    public void initialSetup(){
        driver=BrowserManager.setupBrowser();
        System.out.println("Browser: "+Environment.browser);
    }

    @AfterTest(groups = {"Web"},alwaysRun = true)
    public void closeActivities() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @BeforeMethod(groups = {"Web"},alwaysRun = true)
    public  void beforeMethod(Method method){
        Log.startTestCase(method.getName());
    }
    @AfterMethod(groups = {"Web"},alwaysRun = true)
    public void afterMethod(ITestResult testResult) throws IOException {
        Reporter.setCurrentTestResult(testResult);
        Log.endTestCase(testResult.getTestName());
        if(testResult.getStatus()== ITestResult.FAILURE){
            Reporter.log(testResult.getMethod().getMethodName()+" test case failed.");
            Reporter.log("This is custom log message");
            String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
            Reporter.log("<a href='"+System.getProperty("user.dir")+"/target/screenshots/"+testResult.getMethod().getMethodName()+".jpg"+"'><img src='data:image/png;base64, "+screenshotBase64.toString()+"' height='300' width='600' /></a>");
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File(System.getProperty("user.dir")+"/target/screenshots/"+testResult.getMethod().getMethodName()+".jpg"));
        }
    }
}
