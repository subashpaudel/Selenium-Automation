package org.F1Soft.TestCases;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.F1Soft.Setups.AppiumBase;
import org.F1Soft.Utils.Appium.AndroidGestures;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumTest extends AppiumBase {
    AndroidGestures aG= new AndroidGestures();

    @Test
    public void newTest() throws InterruptedException {
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Text\")")).click();
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Linkify\")")).click();
        String text =androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.hmh.api:id/text2\")")).getText();
        System.out.println(text);
    }

    @Test
    public void test2(){
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
        androidDriver.findElement(By.xpath("(//android.widget.TextView[@text='Buttons'])[1]")).click();
        androidDriver.findElement(By.id("com.hmh.api:id/button_small")).click();
    }

    @Test
    public void longPressTest() throws InterruptedException {
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
        androidDriver.findElement(By.xpath("(//android.widget.TextView[@text='Expandable Lists'])")).click();
        androidDriver.findElement(By.xpath("(//android.widget.TextView[@text='1. Custom Adapter'])")).click();
        WebElement popeleNames= androidDriver.findElement(By.xpath("(//android.widget.TextView[@text='People Names'])"));

        aG.longPress(popeleNames,1000);

        Assert.assertTrue(androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sample menu']")).isDisplayed());
    }


    @Test
    public void scrollTest(){
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
//       scrollTillText("ScrollBars");
        aG.scrollTillEnd("down");
    }

    @Test
    public void testKeyPressandRotate(){
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
        androidDriver.rotate(new DeviceRotation(0,0,90));
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
    }


    @Test
    public void swipeGestureTest() throws InterruptedException {

        Activity activity = new Activity("com.hmh.api", "com.hmh.api.view.Gallery1");
        androidDriver.startActivity(activity);
        WebElement firstImage = androidDriver.findElement(By.xpath("//android.widget.ImageView[1]"));
        Assert.assertTrue(firstImage.getAttribute("selected").equals("true"));
        aG.swipeView(firstImage,"left",0.35);
        Assert.assertTrue(firstImage.getAttribute("selected").equals("false"));
        aG.goBack();
        Thread.sleep(3000);
    }

}

///tagName[@attribute=value]
////android.widget.TextView[@resource-id='android:id/Text1']