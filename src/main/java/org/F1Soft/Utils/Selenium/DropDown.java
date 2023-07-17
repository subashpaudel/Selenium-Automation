package org.F1Soft.Utils.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DropDown {

    WebDriver driver;
    List<WebElement> dropdownItems;



    public  DropDown(WebElement dropdownElement, WebDriver driver){
        this.driver=driver;
        dropdownElement.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dropdownItems= dropdownElement.findElements(By.xpath(".//li/span"));
    }
    public void selectByIndex(Integer index){
        dropdownItems.get(index).click();
    }
    public void selectByValue(String value){
        for(WebElement dropdownItem: dropdownItems){
            if(dropdownItem.getText().trim().equals(value))
            {
                dropdownItem.click();
                break;
            }
        }
    }
    public List<String > getAllOptions(){
        List<String> options = dropdownItems.stream().map(s->s.getText()).collect(Collectors.toList());
        return options;
    }
}
