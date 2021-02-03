package com.telran.scheduler.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperBase {

    AppiumDriver driver;

    public HelperBase(AppiumDriver driver) {
        this.driver = driver;
    }

    public void tap(By locator) {
        driver.findElement(locator).click();

    }

    public void type(By locator, String text) {
        if (text != null) {
            if (!text.equals(driver.findElement(locator).getText())) {
                tap(locator);
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void hideKeyboard() {
        driver.hideKeyboard();
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void swipeRightToLeft() {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int y = size.height / 5;
        int startX = (int) (size.width * 0.8);
        int stopX = (int) (size.width * 0.2);

        action.longPress(PointOption.point(startX, y)).
                waitAction(WaitOptions.waitOptions
                        (Duration.ofSeconds(3))).moveTo
                (PointOption.point(stopX, y)).release().perform();

    }

    public WebElement waitForElement(By locator, int timeOut) {
        return new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
