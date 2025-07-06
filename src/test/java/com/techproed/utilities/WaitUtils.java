package com.techproed.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    /**
     * Waits for the page to load completely when a new page is opened.
     * @param timeout Maximum wait time for the page to load (seconds).
     */
    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }

    /**
     * Waits for the visibility of the specified element using Fluent Wait.
     * @param xpath The xpath of the element.
     * @param withTimeout Maximum wait time (seconds).
     * @param pollingEvery Frequency of checking the element (seconds).
     * @return The expected element.
     */
    public static WebElement fluentWait(String xpath, int withTimeout, int pollingEvery) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(withTimeout)) // Total wait time
                .pollingEvery(Duration.ofSeconds(pollingEvery)) // Check frequency
                .withMessage("Ignoring No Such Element Exception")
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element;
    }

    /**
     * Waits for the specified duration (hard wait).
     * @param seconds Time to wait (seconds).
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for the specified element to be visible.
     * @param element The WebElement to wait for visibility.
     * @param timeout Maximum wait time (seconds).
     * @return The visible WebElement.
     */
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the element with the specified locator to be visible.
     * @param locator The locator to wait for visibility.
     * @param timeout Maximum wait time (seconds).
     * @return The visible WebElement.
     */
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for the specified element to be clickable.
     * @param element The WebElement to wait for clickability.
     * @param timeout Maximum wait time (seconds).
     * @return The clickable WebElement.
     */
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for the element with the specified locator to be clickable.
     * @param locator The locator to wait for clickability.
     * @param timeout Maximum wait time (seconds).
     * @return The clickable WebElement.
     */
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}