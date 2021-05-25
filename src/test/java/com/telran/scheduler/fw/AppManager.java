package com.telran.scheduler.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppManager {

    AppiumDriver driver;
    DesiredCapabilities caps;

    UserHelper user;
    EventHelper event;

    public void init() throws MalformedURLException {
        caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0.0");
        caps.setCapability("deviceName", "qa11_mob");
        caps.setCapability("automationName", "Appium");
        caps.setCapability("appPackage", "com.example.svetlana.scheduler");
        caps.setCapability("appActivity", "presentation.splashScreen.SplashScreenActivity");
        caps.setCapability("app", "/Users/anastasiaivanova/Учеба/v.0.0.3.apk");

        driver = new AndroidDriver(new URL("http://127.0.01:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = new UserHelper(driver);
        event=new EventHelper(driver);
    }

    public EventHelper event() {
        return event;
    }

    public UserHelper user() {
        return user;
    }

    public void stop() {
        driver.quit();
    }

    public String getAppVersion() {
        return driver.findElement(By.id("app_version_res")).getText();
    }
}
