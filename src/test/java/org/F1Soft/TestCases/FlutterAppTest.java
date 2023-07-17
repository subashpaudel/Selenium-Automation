package org.F1Soft.TestCases;

import io.appium.java_client.AppiumBy;
import org.F1Soft.Setups.AppiumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class FlutterAppTest extends AppiumBase {

    @Test
    public void buttomTabNavigation() {
        androidDriver.findElement(AppiumBy.accessibilityId("Next")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("Next")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("Next")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("I already have an account. Sign in!")).click();
       List<WebElement> tabs= androidDriver.findElements(By.xpath("//android.view.View[last()]/following-sibling::android.widget.ImageView")) ;
//       System.out.println(tabs.size());
//       tabs.get(tabs.size()-1).click();
       System.out.println("content-desc: "+tabs.get(0).getAttribute("content-desc"));
        androidDriver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc,'More')]")).click();
    }

}
