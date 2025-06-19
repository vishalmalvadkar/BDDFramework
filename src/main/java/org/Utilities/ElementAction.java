package org.Utilities;

import org.DriverManager.DriverSetUp;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementAction {
    private static final Logger logger = LoggerUtil.getLogger(ElementAction.class);

    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverSetUp.getDriver(), Duration.ofSeconds(30));
    }

    public static void click(WebElement element) {
        logger.info("Attempting to click on element: " + element);
        getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
        logger.info("Clicked on element: " + element);
    }

    public static void type(WebElement element, String text) {
        logger.info("Typing into element: " + element + " | Text: " + text);
        WebElement visibleElement = getWait().until(ExpectedConditions.visibilityOf(element));
        visibleElement.clear();
        visibleElement.sendKeys(text);
        logger.info("Text entered successfully");
    }

    public static String getText(WebElement element) {
        logger.info("Getting text from element: " + element);
        String text = getWait().until(ExpectedConditions.visibilityOf(element)).getText();
        logger.info("Text retrieved: " + text);
        return text;
    }

    public static boolean isVisible(WebElement element) {
        logger.info("Checking visibility of element: " + element);
        try {
            boolean visible = getWait().until(ExpectedConditions.visibilityOf(element)).isDisplayed();
            logger.info("Element visible: " + visible);
            return visible;
        } catch (Exception e) {
            logger.warn("Element not visible", e);
            return false;
        }
    }
}
