package org.F1Soft.PagePactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Login {
    WebDriver driver;
    By userName_TextBox= By.id("id");

    public Login(WebDriver driver) {
        this.driver=driver;
    }

    public void typUserName(String username){
//        TO-DO Implement step
    }
    public void typPassword(String password){
//        TO-DO Implement step
    }

    public void clickLoginButton(){
//        TO-DO Implement step
    }

    public void login(String userName, String password,String shouldLogin){
        typUserName(userName);
        typPassword(password);
        clickLoginButton();
        System.out.println(String.format("userName= %s ; password= %s",userName,password));
        Assert.assertEquals(shouldLogin,"Y");
    }
}
