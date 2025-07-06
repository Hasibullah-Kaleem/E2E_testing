package com.techproed.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class BrowserUtils {
    /**
     * Tries to click on the specified element for a certain period.
     * @param element WebElement to be clicked.
     * @param timeout Timeout duration (seconds).
     */
    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                WaitUtils.waitFor(1);
            }
        }
    }

    /**
     * Tries to get the text of the specified element for a certain period.
     * @param element WebElement whose text will be retrieved.
     * @param timeout Timeout duration (seconds).
     * @return Retrieved text.
     */
    public static String getTextWithTimeout(WebElement element, int timeout) {
        String text = "";
        for (int i = 0; i < timeout; i++) {
            try {
                text = element.getText();
                return text;
            } catch (WebDriverException e) {
                WaitUtils.waitFor(1);
            }
        }
        return null;
    }

    /**
     * Tries to send text to the specified element for a certain period.
     * @param element WebElement to send text to.
     * @param text Text to be sent.
     * @param timeout Timeout duration (seconds).
     */
    public static void sendKeysWithTimeout(WebElement element, String text, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                WaitUtils.waitFor(1);
            }
        }
    }

    /**
     * Clicks on the radio button at the specified index.
     * @param index Index of the radio button to be clicked.
     */
    public void radioClickByIndex(int index) {
        int numOfRadio = Driver.getDriver().findElements(By.xpath("//input[@type='radio']")).size();
        for (int i = 0; i < numOfRadio; i++) {
            if (!Driver.getDriver().findElements(By.xpath("//input[@type='radio']")).get(index).isSelected()) {
                Driver.getDriver().findElements(By.xpath("//input[@type='radio']")).get(index).click();
            }
        }
    }

    /**
     * Clicks on the checkbox at the specified index.
     * @param index Index of the checkbox to be clicked.
     */
    public void checkboxClickByIndex(int index) {
        int numOfCheckbox = Driver.getDriver().findElements(By.xpath("//input[@type='checkbox']")).size();
        try {
            for (int i = 0; i < numOfCheckbox; i++) {
                if (!Driver.getDriver().findElements(By.xpath("//input[@type='checkbox']")).get(index).isSelected()) {
                    Driver.getDriver().findElements(By.xpath("//input[@type='checkbox']")).get(index).click();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Selects an option from a dropdown menu by visible text.
     * @param element Dropdown WebElement.
     * @param text Text to be selected.
     */
    public static void dropdownSelectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equalsIgnoreCase(text)) {
                select.getOptions().get(i).click();
                break;
            }
        }
    }

    /**
     * Selects an option from a dropdown menu by index.
     * @param element Dropdown WebElement.
     * @param index Index to be selected.
     */
    public static void dropdownSelectByIndex(WebElement element, int index) {
        Select objSelect = new Select(element);
        objSelect.selectByIndex(index);
    }

    /**
     * Selects an option from a dropdown menu by value.
     * @param element Dropdown WebElement.
     * @param value Value to be selected.
     */
    public static void dropdownSelectByValue(WebElement element, String value) {
        Select objSelect = new Select(element);
        objSelect.selectByValue(value);
    }

    /**
     * Selects a random option from a dropdown menu and returns the selected WebElement.
     * @param select Dropdown Select object.
     * @return Selected WebElement.
     */
    public static WebElement dropdownSelectRandomText(Select select) {
        Random random = new Random();
        List<WebElement> list = select.getOptions();
        int optionIndex = 1 + random.nextInt(list.size() - 1);
        select.selectByIndex(optionIndex);
        return select.getFirstSelectedOption();
    }
}