package com.telran.scheduler.tests;

import com.telran.scheduler.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenAppTest extends TestBase {

    @Test
    public void testLaunchApp(){
        String version = app.getAppVersion();
        System.out.println("app opened");
        Assert.assertEquals(version, "0.0.3");
    }

    @Test
    public void checkThatCreatedEventIsPresent(){
        app.user().login(new User().email("ana@gmail.com").password("Ana123456"));
        app.event().multiSwipe(3);
    }
}
