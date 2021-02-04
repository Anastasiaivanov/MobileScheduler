package com.telran.scheduler.tests;

import com.telran.scheduler.model.User;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventDeleteTest extends TestBase {

    @BeforeMethod
    public void preconditions(){
        if(!app.event().isIconSortOptionsPresent()){
            app.user().login(new User().email("ana@gmail.com").password("Ana123456"));
        }
    }

    @Test
    public void deleteFirstEventTest() {
        app.event().deleteFirstEvent();
    }
}
