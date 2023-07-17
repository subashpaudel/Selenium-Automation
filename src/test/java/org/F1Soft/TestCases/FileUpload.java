package org.F1Soft.TestCases;

import org.F1Soft.Setups.BaseSetups;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;

public class FileUpload extends BaseSetups {

    @Test
    public void fileUploadExample() throws IOException {
        driver.get("https://www.iloveimg.com/crop-image");
//        driver.findElement(By.xpath("//*[@id=\"pickfiles\"]")).click();
//        Runtime.getRuntime().exec("C:\\automation\\src\\test\\resources\\uploadscript.exe");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement header = driver.findElement(By.xpath("//*[@id=\"workArea\"]/div[2]"));
        jsExecutor.executeScript("arguments[0].style.border='2px solid red'", header);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
