package org.F1Soft.Setups;

import org.F1Soft.Helpers.Environment;
import org.F1Soft.Helpers.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v111.emulation.Emulation;
import org.openqa.selenium.devtools.v111.fetch.Fetch;
import org.openqa.selenium.devtools.v111.network.Network;
import org.openqa.selenium.devtools.v111.network.model.ErrorReason;
import org.openqa.selenium.devtools.v111.network.model.Request;
import org.openqa.selenium.devtools.v111.network.model.Response;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BrowserManager {

    public static   WebDriver setupBrowser(){
        WebDriver driver=null;

        if(Environment.browser.equalsIgnoreCase("microsoftEdge")){
            driver=new EdgeDriver();
        }
        else{
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
//        if(new ConfigHelper().getProperty("BROWSER").equalsIgnoreCase("chrome")) {
            driver =(WebDriver) new ChromeDriver(options);
        }

        ChromeDriver driver1= (ChromeDriver) driver;

        DevTools devTools = driver1.getDevTools();
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


//        driver1.executeCdpCommand("Emulation.setDeviceMetricsOverride",params);



        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        devTools.addListener(Network.responseReceived(),responseReceived -> {
            Response response =responseReceived.getResponse();
            if(response.getStatus()!=200)
                Log.error("API -> "+response.getStatus()+":"+response.getUrl());
        });
        devTools.send(Emulation.setGeolocationOverride(Optional.of(24.694969),Optional.of(46.724129),Optional.empty()));
        driver.get("https://www.netflix.com/");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://hub.fonepay.com/");

        devTools.send(Fetch.enable(Optional.empty(),Optional.empty()));

        devTools.addListener(Fetch.requestPaused(),requestPaused -> {
            if (requestPaused.getRequest().getUrl().contains("fonepay-merchant-api-web/airlines/sectors")){
                Request request = requestPaused.getRequest();
                String modifiedURl= requestPaused.getRequest().getUrl().replace("sector","replace");
            devTools.send(Fetch.failRequest(requestPaused.getRequestId(), ErrorReason.FAILED));
        }
        else {
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                        ));
            }
        });




        return driver;
    }
}
