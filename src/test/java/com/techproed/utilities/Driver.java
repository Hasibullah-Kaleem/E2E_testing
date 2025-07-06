package com.techproed.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    /*
    We created a ThreadLocal object of type WebDriver so that each thread has its own driver 
    while running parallel tests. This ensures that parallel-running threads do not interfere 
    with each other’s execution.
    */
    
    // Using ThreadLocal to create a separate WebDriver instance for each thread.
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            // Creating a WebDriver instance for each thread.
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    driverPool.set(new ChromeDriver());
                    break;
                case "edge":
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    driverPool.set(new SafariDriver());
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    driverPool.set(new ChromeDriver());
            }

            // Configuring the created WebDriver instance.
            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        // Returning the WebDriver instance specific to the current thread.
        return driverPool.get();
    }

    private Driver() {
        // Singleton pattern to prevent instantiation.
    }

    public static void closeDriver() {
        // Closing the WebDriver instance if it's active.
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove(); // Removing the reference from ThreadLocal.
        }
    }
}