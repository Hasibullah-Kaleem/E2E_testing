package com.techproed.utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtils {
    private static Actions actions = new Actions(Driver.getDriver());

    /**
     * Performs a right-click (context click) on the specified element.
     *
     * @param element WebElement to perform the right-click on.
     */
    public static void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    /**
     * Performs a double-click on the specified element.
     *
     * @param element WebElement to perform the double-click on.
     */
    public static void doubleClick(WebElement element) {
        actions.doubleClick(element).build().perform();
    }

    /**
     * Performs a hover action over the specified element.
     *
     * @param element WebElement to hover over.
     */
    public static void hoverOver(WebElement element) {
        actions.moveToElement(element).perform();
    }

    /**
     * Scrolls down the page using the PAGE_DOWN key.
     */
    public static void scrollDown() {
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    /**
     * Scrolls up the page using the PAGE_UP key.
     */
    public static void scrollUp() {
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    /**
     * Scrolls the page to the right using the ARROW_RIGHT key.
     */
    public static void scrollRight() {
        actions.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT).perform();
    }

    /**
     * Scrolls the page to the left using the ARROW_LEFT key.
     */
    public static void scrollLeft() {
        actions.sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT).perform();
    }

    /**
     * Scrolls to the top of the page using the HOME key.
     */
    public static void scrollHome() {
        actions.sendKeys(Keys.HOME).perform();
    }

    /**
     * Scrolls to the bottom of the page using the END key.
     */
    public static void scrollEnd() {
        actions.sendKeys(Keys.END).perform();
    }

    /**
     * Drags the source element and drops it onto the target element.
     *
     * @param source WebElement to be dragged.
     * @param target WebElement where the source will be dropped.
     */
    public static void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    /**
     * Drags the source element by a specified offset in both horizontal (x) and vertical (y) directions.
     *
     * @param source  WebElement to be dragged.
     * @param xOffset Horizontal movement distance (positive for right, negative for left).
     * @param yOffset Vertical movement distance (positive for down, negative for up).
     */
    public static void dragAndDropBy(WebElement source, int xOffset, int yOffset) {
        actions.dragAndDropBy(source, xOffset, yOffset).perform();
    }

    /**
     * Simulates pressing the TAB key to switch between elements.
     */
    public static void pressTab() {
        actions.sendKeys(Keys.TAB).build().perform();
    }
}