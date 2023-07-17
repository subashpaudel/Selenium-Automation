package org.F1Soft.Utils.Appium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.F1Soft.Utils.Java.Global;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import static org.F1Soft.Utils.Java.Global.androidDriver;

public class AndroidGestures {

    public  void longPress(WebElement element, Integer duration){
        ((JavascriptExecutor) androidDriver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration",duration

        ));
    }

    public void scrollTillText(String text){
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))"));
    }


    public void scrollTillEnd(String direction){
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: flingGesture", ImmutableMap.of(
                    "top", 100,
                    "left", 100,
                    "width", 200,
                    "height", 300,
                    "direction", direction,
                    "speed", 500
            ));
        }
        while (canScrollMore);
    }

    public boolean scrollAndFindElement(AppiumBy appiumBy, String direction){
        boolean found=false;
        boolean canScrollMore;
        do {
            try{
                WebElement element =androidDriver.findElement(appiumBy);
                if(element.isDisplayed()){
                    found=true;
                }
            }
            catch (Exception exception){
                System.out.println("Not found yet");
            }
            canScrollMore = (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: flingGesture", ImmutableMap.of(
                    "top", 100,
                    "left", 100,
                    "width", 200,
                    "height", 300,
                    "direction", direction,
                    "speed", 500
            ));
        }
        while (canScrollMore && !found);
        return found;
    }


    public void swipeView(WebElement ele, String direction , Double percent){
        ((JavascriptExecutor) androidDriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId",  ((RemoteWebElement)ele).getId() ,
                "direction", direction,
                "percent", percent
        ));
    }

    public void goBack(){
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

}
