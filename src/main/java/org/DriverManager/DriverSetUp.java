package org.DriverManager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetUp {

    private static ThreadLocal<String> browserName = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void setBrowser(String browser) {
        browserName.set(browser);
    }

    public static void initDriver() {
        if (tlDriver.get() == null) {
            String browser = browserName.get();

            if (browser == null) {
                throw new RuntimeException("Browser not set. Use setBrowser() before initDriver().");
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    tlDriver.set(new ChromeDriver());
                    break;
                case "firefox":
                    tlDriver.set(new FirefoxDriver());
                    break;
                default:
                    throw new RuntimeException("Invalid browser: " + browser);
            }
        }
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
        browserName.remove();
    }
}
