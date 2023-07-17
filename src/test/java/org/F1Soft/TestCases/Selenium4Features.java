package org.F1Soft.TestCases;

import org.F1Soft.Helpers.Log;
import org.F1Soft.Setups.BaseSetups;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Selenium4Features extends BaseSetups {


    @Test(groups = {"Web"}, priority = 2)
    public void relativeLocatorExample() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Domestic Flights']")).click();
        WebElement selectFrom = driver.findElement(By.xpath("//p-dropdown[@formcontrolname='sectorFrom']"));
        WebElement label = driver.findElement(with(By.tagName("label")).above(selectFrom));
        System.out.println(label.getText());
        Thread.sleep(10000);
        LogEntries logs =driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logEntities= logs.getAll();
        for (LogEntry log:logEntities) {
            if(log.getMessage().toLowerCase().contains("error"))
            Log.error("CONSOLE -> "+log.getMessage()+": Level->"+log.getLevel());
        }
    }

    @Parameters({"emailId"})
    @Test(groups = {"Web"}, enabled = false)
    public  void switchWindows(@Optional("example.com") String emailId){
        Log.info("EmaildId: '"+emailId+"'");
        driver.get("https://hub.fonepay.com/");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://saucelabs.com/resources/blog/selenium-4-relative-locators");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.cricbuzz.com/cricket-match/live-scores");
       String parent = driver.getWindowHandle();
       String title= "Fonepay Merchant Hub";
        Log.typing("PasswordTextBox","password");
        Log.warn("This is warning message");
        Log.error("This is failing");
        Set<String> allWindows = driver.getWindowHandles();
        Iterator iterator= allWindows.iterator();
        while(iterator.hasNext()) {
            String nextWindow = (String) iterator.next();
            driver.switchTo().window(nextWindow);
            if(driver.getTitle().equals(title)){
                System.out.println(driver.getCurrentUrl());
                break;
            }
        }
        driver.switchTo().window(parent);

    }

    @Test(groups = {"Web"}, priority = 7, enabled = false)
    public  void sizeExample() throws IOException {
        WebElement searchBox = driver.findElement(By.cssSelector("div[class='form-group bc-form-group']"));
        Dimension dim = searchBox.getSize();
        System.out.println(dim.getHeight()+ ": " +dim.getWidth());
        File SrcFile= searchBox.getScreenshotAs(OutputType.FILE);
//        File DestFile=new File("Downloads\\newFile.png");
        File dest = new File(System.getProperty("user.dir") +    "/screenshots/elementLogo.png");
//Copy file at destination
        FileUtils.copyFile(SrcFile, dest);
    }


}
