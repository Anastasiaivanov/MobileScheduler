package com.telran.scheduler.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void testLogin() {
        TestBase.app.user().login("ana@gmail.com", "Ana123456");
        Assert.assertTrue(TestBase.app.event().isIconSortOptionsPresent());
    }
}
