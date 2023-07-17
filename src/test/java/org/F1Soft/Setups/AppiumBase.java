package org.F1Soft.Setups;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.F1Soft.Utils.Java.ConfigHelper;
import org.F1Soft.Utils.Java.Global;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumBase {
     public AndroidDriver androidDriver;
     ConfigHelper cH= new ConfigHelper();
    AppiumDriverLocalService service;
    @BeforeSuite(alwaysRun = true)
    public void setUpAppiumTest() throws MalformedURLException, InterruptedException {
        File apkFile = new File("src/test/resources/flutter_mock_app.apk");
        String AppuimJSPath= cH.getProperty("APPIUM_MAINJS_PATH");
        cH.setProperty("NEW_PROP","VALUE");
        System.out.println(AppuimJSPath);
        System.out.println(cH.getProperty("NEW_PROP"));

//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
//                .withAppiumJS(new File(cH.getProperty("APPIUM_MAINJS_PATH")))
//                .withIPAddress("127.0.0.1")
//                .withLogFile(new File("./target/logs/appiumLog"))
//                .usingPort(4723);
//        service= new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\User\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();
//        service= AppiumDriverLocalService.buildService(builder);
//        service.start();
//
//        Thread.sleep(5000);

        UiAutomator2Options options = new UiAutomator2Options()
                .autoGrantPermissions()
                .setDeviceName("TestDevice")
                .setApp(apkFile.getAbsolutePath());
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Global.setAndroidDriver(androidDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void stopServices(){
//        service.stop();
    }
}
