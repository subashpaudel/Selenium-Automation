package org.F1Soft.TestCases;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.F1Soft.Utils.Appium.AndroidGestures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobBank extends AppiumTest{
    AndroidGestures aG = new AndroidGestures();

    @Test
    public  void  laxmiBankFeedBack() throws InterruptedException {
        Thread.sleep(5000);
        androidDriver.findElement(By.id("com.laxmibank.mobilemoney.uat:id/lg_app_tac_agree_checkbox")).click();
        androidDriver.findElement(By.id("com.laxmibank.mobilemoney.uat:id/lg_app_tac_accept")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("More")).click();
        androidDriver.findElements(AppiumBy.id("com.laxmibank.mobilemoney.uat:id/cvMenu")).get(3).click();
//        WebElement validationMessage= androidDriver.findElement(AppiumBy.id("com.laxmibank.mobilemoney.uat:id/textinput_error"));
//        Assert.assertTrue(validationMessage.isDisplayed());


        WebElement mobileTextBox= androidDriver.findElement(AppiumBy.xpath("//android.widget.EditText[@focused='true']"));

//        mobileTextBox.sendKeys("98137");
        enterText(mobileTextBox,"7788989");
        enterText(mobileTextBox,"");
        mobileTextBox.clear();
        WebElement validationMessage= androidDriver.findElement(AppiumBy.id("com.laxmibank.mobilemoney.uat:id/textinput_error"));
        Assert.assertTrue(validationMessage.isDisplayed());
        System.out.println(validationMessage.getText());

        enterText(mobileTextBox,"9813798508");
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
        androidDriver.hideKeyboard();
        enterText(androidDriver.findElement(By.id("com.laxmibank.mobilemoney.uat:id/textinput_placeholder")) ,"Roshan Shah");
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
        androidDriver.hideKeyboard();
        enterText(androidDriver.findElement(By.id("com.laxmibank.mobilemoney.uat:id/textinput_placeholder")) ,"roshanshah.011@gmail.com");
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
        androidDriver.hideKeyboard();
       WebElement dropdown= androidDriver.findElement(AppiumBy.className("android.widget.Spinner"));
//       dropdown.click();

       Actions act = new Actions(androidDriver);
       WebElement label = androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Nature of Problem\")"));
//       act.moveToElement(label).moveByOffset(-50,150).click().build().perform();
//        System.out.println(androidDriver.getPageSource());
       // androidDriver.findElement(AppiumBy.androidUIAutomator("text(\"Banking Facility\")")).click();



        WebElement descriptionBox= getTextBoxNextToLabel("Description of Problem");

        descriptionBox.click();
        descriptionBox.sendKeys("description");
        androidDriver.hideKeyboard();
        WebElement issueDateTextBox = getTextBoxNextToLabel("Issue Date");
        issueDateTextBox.sendKeys("2023-05-09");
        Assert.assertTrue(issueDateTextBox.getAttribute("text").equals("2023-05-09"));
        aG.scrollTillEnd("down");
        WebElement issueTimeTextBox = getTextBoxNextToLabel("Issue Time");
        issueTimeTextBox.sendKeys("04:08");
        Assert.assertTrue(issueTimeTextBox.getAttribute("text").equals("04:08"));
         androidDriver.findElement(By.xpath("//android.widget.Button[@text='Proceed']")).click();
         String title= androidDriver.findElement(By.id("com.laxmibank.mobilemoney.uat:id/tvTitle")).getText();
         String message = androidDriver.findElement(By.id("com.laxmibank.mobilemoney.uat:id/tvMessage")).getText();
         Assert.assertEquals(title,"ERROR");
         Assert.assertEquals(message, "Provided details are invalid. Please check and try again.");
         androidDriver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(5000);

        AppiumBy appiumBy= (AppiumBy) AppiumBy.xpath("id");
        if(aG.scrollAndFindElement(appiumBy,"down")){
            ifFound();
        }
        else{
             if(aG.scrollAndFindElement(appiumBy,"up")){
                 ifFound();
            }
             else {
//                 TO-DO
//                 when not found
             }
        }


        //android.widget.EditText
    }


    private WebElement getTextBoxNextToLabel(String label){
        return  androidDriver.findElement(By.xpath("//android.widget.TextView[@text='"+label+"']/following-sibling::android.widget.LinearLayout[1] //android.widget.EditText"));
    }

    private void enterText(WebElement ele,String text){
        Actions action = new Actions(androidDriver);
        action.click(ele).sendKeys(text).build().perform();
    }

    public void ifFound(){

    }
}
