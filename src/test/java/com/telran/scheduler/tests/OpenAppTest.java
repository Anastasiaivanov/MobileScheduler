package com.telran.scheduler.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class OpenAppTest extends TestBase {

    @Test
    public void testLaunchApp(){
        System.out.println("app opened");
    }
}
