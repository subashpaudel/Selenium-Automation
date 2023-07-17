package org.F1Soft.Utils.Java;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

public class Global {
    public static AndroidDriver androidDriver;
    public  static WebDriver driver;

    public static void setAndroidDriver(AndroidDriver driver){
        androidDriver=driver;
    }
}
