package org.F1Soft.TestCases;

import org.F1Soft.Helpers.Log;
import org.F1Soft.Setups.BaseSetups;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v111.emulation.Emulation;
import org.openqa.selenium.devtools.v111.network.Network;
import org.openqa.selenium.devtools.v111.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GeolocationTest  {

    @Test
    public void googleTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

//        devTools.send(Emulation.setDeviceMetricsOverride(
//                325,
//                600,
//                .5,
//                true,
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty()));

        Map params = new HashMap<>();

        params.put("width",400);
        params.put("height",650);
        params.put("deviceScaleFactor",10);
        params.put("mobile",false);


        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",params);



        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        devTools.addListener(Network.responseReceived(),responseReceived -> {
            Response response =responseReceived.getResponse();
            if(response.getStatus()!=200)
                Log.error("API -> "+response.getStatus()+":"+response.getUrl());
        });
//        devTools.send(Emulation.setGeolocationOverride(Optional.of(24.694969),Optional.of(46.724129),Optional.empty()));
        Map params1 = new HashMap<>();
            params.put("latitude",24.694969);
                    params.put("longitude", 46.724129);
                params.put( "accuracy", 100);

        driver.executeCdpCommand("Emulation.setGeolocationOverride", params1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.get("https://google.com");
        driver.findElement(By.className("gLFyf")).sendKeys("netflix");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
