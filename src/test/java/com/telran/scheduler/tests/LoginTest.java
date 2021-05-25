package com.telran.scheduler.tests;

import com.telran.scheduler.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void testLogin() {
        app.user().login(new User().email("ana@gmail.com").password("Ana123456"));
        Assert.assertTrue(app.event().isIconSortOptionsPresent());
    }
}
