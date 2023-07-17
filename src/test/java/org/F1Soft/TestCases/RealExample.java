package org.F1Soft.TestCases;

import org.F1Soft.Setups.BaseSetups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class RealExample extends BaseSetups {



    @BeforeMethod()
    public void beforeMethod() {
        System.out.println("This is called before method");
    }


    @Test(groups = {"Web"}, priority =2 )
    public void searchFlights() throws InterruptedException {
        String from = "JANAKPUR";
        String to = "KATHMANDU";

        SoftAssert sA = new SoftAssert();


        driver.get("https://hub.fonepay.com/");
        System.out.println("This is searchFlights  test case");
        driver.findElement(By.xpath("//a[text()='Domestic Flights']")).click();
        WebElement arrivalDatePicker = driver.findElement(By.xpath("//div[@class='select select__datepicker']/input[@formcontrolname='arrivalDate']"));
        if (arrivalDatePicker.isEnabled()) {
            driver.findElement(By.xpath("//span[text()='One Way']")).click();
        }
        Thread.sleep(2000);
        WebElement dropDown = driver.findElement(By.xpath("//p-dropdown[@formcontrolname='sectorFrom']/div[contains(@class,'ui-dropdown')]/label"));
        dropDown.click();

//        Actions a = new Actions(driver);
//        a.moveToElement(dropdown)
        driver.findElement(By.xpath("//p-dropdown[@formcontrolname='sectorFrom'] //input[@placeholder='Search Location']")).sendKeys("JAN");
        List<WebElement> allDropDownListItems = driver.findElements(By.xpath("//li[contains(@class,'ui-dropdown-item')]"));
        boolean didFound = clickIFItemPresent(allDropDownListItems, from);
        sA.assertTrue(didFound, "From location was not found in dropdown list.");


        WebElement dropDownTo = driver.findElement(By.xpath("//p-dropdown[@formcontrolname='sectorTo']/div[contains(@class,'ui-dropdown')]/label"));
        dropDownTo.click();
        driver.findElement(By.xpath("//p-dropdown[@formcontrolname='sectorTo'] //input[@placeholder='Search Location']")).sendKeys("KAT");
        allDropDownListItems = driver.findElements(By.xpath("//li[contains(@class,'ui-dropdown-item')]"));
        didFound = clickIFItemPresent(allDropDownListItems, to);
        sA.assertTrue(didFound, "To location was not found in dropdown list.");


        WebElement datePicker = driver.findElement(By.xpath("//div[@class='select select__datepicker']/input[@formcontrolname='departureDate']"));
        selectDateFromDatePicker(datePicker, "24", "May","2023", sA);
        Thread.sleep(3000);


        Select select = new Select(driver.findElement(By.cssSelector("select[formcontrolname='noOfAdult']")));
        select.selectByValue("2");
        driver.findElement(By.cssSelector("button[class='btn btn-primary btn-block-mobile ng-star-inserted']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table/thead/tr/th")));

       List<WebElement> columnHeaders= driver.findElements(By.xpath("//table/thead/tr/th"));
       List<String> allColumns = columnHeaders.stream().map(s->s.getText().trim()).collect(Collectors.toList());

       List<WebElement> fareElements = driver.findElements(By.xpath(String.format("//table/tbody/tr/td[%s]/span/div[@class!='secondary-detail-text ng-star-inserted']",allColumns.indexOf("FARE")+1)));
       List<Integer> allFares = fareElements.stream().map(s->parseInt((s.getText().replace(",","")))).sorted().collect(Collectors.toList());

       System.out.println("Lowest Price : "+ allFares.get(0));

       List<WebElement> departureElements = driver.findElements(By.xpath(String.format("//table/tbody/tr/td[%s]/span/div[@class ='secondary-detail-text ng-star-inserted']/span",allColumns.indexOf("DEPARTURE")+1)));
       List<String> departures = departureElements.stream().map(s->s.getText()).sorted().collect(Collectors.toList());
       for(String s: departures){
           System.out.println(s);
       }
       sA.assertTrue(departures.contains("16:45"), "departure time 16:45 not available. ");
        sA.assertAll();
    }

//    @Test(priority = 1)
//    public void anotherTest(){
//        System.out.println("This is anotherTest  test case");
//        driver.get("https://google.com/");
//    }

//    @AfterMethod
//    public void exampleForAfterMethod() {
//        System.out.println("This is called after method");
//    }
//
//    @AfterClass
//    public void exampleForAfterClass() throws InterruptedException {
//        System.out.println("This is called after class");
//        Thread.sleep(3000);
//        driver.quit();
//    }

    public boolean clickIFItemPresent(List<WebElement> listItems, String searchText) {
        boolean found = false;
        for (WebElement ele : listItems) {
            if (ele.getText().trim().equals(searchText)) {
                found = true;
                ele.click();
                break;
            }
        }
        return found;
    }

    public void selectDateFromDatePicker(WebElement datePicker, String day, String month, String year, SoftAssert sA) {
        datePicker.click();
        driver.findElement(By.xpath("//div[@class='bs-datepicker-head'] //button[@class='next']/preceding-sibling::button[1]")).click();
        boolean found=false;
        boolean goBack=false;
        do{
            List<WebElement> elementsForYear= driver.findElements(By.cssSelector("td[role='gridcell'] span"));

            List<String> listOfYeas = elementsForYear.stream().map(ele-> ele.getText().trim()).collect(Collectors.toList());
//            for (String yr: listOfYeas){
//                System.out.println(yr);
//            }
            if(listOfYeas.contains(year)){
                elementsForYear.get(listOfYeas.indexOf(year)).click();
                found=true;
            }
            else if(parseInt(listOfYeas.get(0)) > parseInt(year))
                driver.findElement(By.xpath("//div[@class='bs-datepicker-head'] //button[@class='previous']")).click();
            else
                driver.findElement(By.xpath("//div[@class='bs-datepicker-head'] //button[@class='next']")).click();

//            if(parseInt(elementsForYear.get(0).getText()) > parseInt(year))
//                goBack=true;
//            else if (parseInt(elementsForYear.get(elementsForYear.size()-1).getText()) < parseInt(year)) {
//                goBack=false;
//            }
//            else{
//                //the year is displayed, find and click
//                for (WebElement ele : elementsForYear) {
//                    if (ele.getText().trim().equals(year)) {
//                        found = true;
//                        ele.click();
//                        break;
//                    }
//                }
//            }
//            if(!found){
//                if(goBack){
//                    //click back arrow
//                    driver.findElement(By.xpath("//div[@class='bs-datepicker-head'] //button[@class='previous']")).click();
//                }
//                else{
//                    //click next arrow
//                    driver.findElement(By.xpath("//div[@class='bs-datepicker-head'] //button[@class='next']")).click();
//                }
//            }
        }while (!found);

        driver.findElement(By.xpath("//td[@role='gridcell']/span[text()='"+month+"']")).click();
        List<WebElement> allDays= driver.findElements(By.xpath("//td[@role='gridcell']/span"));

        List<WebElement> filterdedDays= allDays.stream().filter(dayElement-> ! dayElement.getAttribute("class").contains("is-other-month")).collect(Collectors.toList());
//        filterdedDays.stream().forEach(d->System.out.println(d.getText()));

        for(WebElement ele : filterdedDays){
            if(ele.getText().equals(day)){
                ele.click();
                break;
            }
        }
        sA.assertEquals(driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-airline-search/div/div[2]/div[3]/form/div/div[1]/div[3]/div[1]/div/input")).getAttribute("value"),"2023/05/24");


    }
}
