package com.techproed.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReusableMethods {
    // HARD WAIT METHOD
    public static void waitForSecond(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Alert ACCEPT
    public static void alertAccept() {
        Driver.getDriver().switchTo().alert().accept();
    }
    
    // Alert DISMISS
    public static void alertDismiss() {
        Driver.getDriver().switchTo().alert().dismiss();
    }
    
    // Alert getText()
    public static String alertText() {
        return Driver.getDriver().switchTo().alert().getText();
    }
    
    // Alert promptBox
    public static void alertPrompt(String text) {
        Driver.getDriver().switchTo().alert().sendKeys(text);
    }
    
    // DropDown VisibleText
    /*
       Select select2 = new Select(gun);
       select2.selectByVisibleText("7");
       // ddmVisibleText(gun,"7"); --> Instead of the above usage, I can handle it with just the method
    */
    public static void ddmVisibleText(WebElement dropdown, String option) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }
    
    // DropDown Index
    public static void ddmIndex(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    
    // DropDown Value
    public static void ddmValue(WebElement dropdown, String option) {
        Select select = new Select(dropdown);
        select.selectByValue(option);
    }
    
    // Switch to Window by Index
    public static void switchToWindow(int index) {
        List<String> allWindowHandles = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(allWindowHandles.get(index));
    }
    
    // Switch to Window by Object
    public static void window(int index) {
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[index].toString());
    }
    
    // EXPLICIT WAIT METHODS
    // Visible Wait
    public static void visibleWait(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    // Visible Element Locator Wait
    public static WebElement visibleWait(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    // Alert Wait
    public static void alertWait(int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    
    // Full Page Screenshot
    public static void screenShot(String name) {
        String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        String filePath = System.getProperty("user.dir") + "/src/com/techproed/testOutputs/screenshots/" + name + date + ".png";
        try {
            Files.write(Paths.get(filePath), ts.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    // WebElement Screenshot
    public static void screenShotOfWebElement(WebElement webElement) {
        String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
        String filePath = System.getProperty("user.dir") + "/src/com/techproed/testOutputs/webElementScreenshots/" + date + ".png";
        try {
            Files.write(Paths.get(filePath), webElement.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    // WebTable
    public static void printData(int row, int column) {
        WebElement rowColumn = Driver.getDriver().findElement(By.xpath("(//tbody)[1]//tr[" + row + "]//td[" + column + "]"));
        System.out.println(rowColumn.getText());
    }
    
    // Click Method
    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }
    
    // JS Scroll
    public static void scroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    // JS Scroll to End of Page
    public static void scrollEnd() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    // JS Scroll to Top of Page
    public static void scrollHome() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }
    
    // JS SendKeys
    public static void sendKeysJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value='" + text + "'", element);
    }
    
    // JS SetAttributeValue
    public static void setAttributeJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value', '" + text + "')", element);
    }
    
    // JS GetAttributeValue
    public static void getValueByJS(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String attributeValue = (String) js.executeScript("return document.getElementById('" + id + "')." + attributeName);
        System.out.println("Attribute Value: = " + attributeValue);
    }
}