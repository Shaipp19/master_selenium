package com;

import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PracticeSelenium_1 {
    @FindBy(id = "dropdown-class-example")
    static Select dropdown;

    @FindBy(name = "enter-name")
    WebElement name;

    @FindBy(id = "alertbtn")
    WebElement alertButton;

    @FindBy(id ="confirmbtn")
    WebElement confirmButton;

    @FindBy(id="opentab")
    WebElement openTabButton;

    @FindBy(css ="div[class='support float-left'] span")
    WebElement supportTextEmail;

    public PracticeSelenium_1(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Add your test steps here
        Select sc = new Select(driver.findElement(org.openqa.selenium.By.id("dropdown-class-example")));
        sc.selectByVisibleText("Option2");

        //Handling alerts
        PracticeSelenium_1 practice = new PracticeSelenium_1(driver);
        practice.handleAlert(driver,"TestUser");
        practice.handleConfirmAlert(driver);
        practice.handleMultipleWindows(driver);


    }

    private void handleMultipleWindows(WebDriver driver) {
        openTabButton.click();
        Set <String> widnows = driver.getWindowHandles();
        Iterator <String> it = widnows.iterator();
        String parentWindow = it.next();
        String childWindow = it.next();
        driver.switchTo().window(childWindow);
        System.out.println("Child window URL: " + driver.getCurrentUrl());
        String emailId = supportTextEmail.getText();
        System.out.println("Support email ID: " + emailId);
        driver.switchTo().window(parentWindow);
        handleAlert(driver,emailId);

    }

    private void handleConfirmAlert(WebDriver driver) {
        name.sendKeys("Shailesh");
        confirmButton.click();
        String confirmText = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();
    }

    public void handleAlert(WebDriver driver, String text){
        name.sendKeys(text); 
        alertButton.click();
        String alertText = driver.switchTo().alert().getText();
        String[] str = alertText.split(",");
        String [] firstPart = str[0].split(" ");
        if(firstPart[1].equals(text)) {
            System.out.println("Alert text is as expected.");
        } else {
            System.out.println("Alert text is NOT as expected.");
        }
        System.out.println("Alert text: " + alertText);
        driver.switchTo().alert().accept();         
    }

}
