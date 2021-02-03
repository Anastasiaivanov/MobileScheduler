package com.telran.scheduler.tests;

import com.telran.scheduler.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCreationTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
        if(!app.event().isIconSortOptionsPresent()){
            app.user().login(new User().email("ana@gmail.com").password("Ana123456"));
        }
    }

    @Test
    public void eventCreationTest() throws InterruptedException {
//tapOnPlusButton
        app.event().initEventCreation();
        app.event().tapOnPencilButton();
        Thread.sleep(5000);
        app.event().fillCreationForm("Event 1", "work",1, "18");
        //app.event().rangeTime("9","15","12","15");
        app.event().pressAddButton();
    }

    @Test
    public void eventCreationChangeDateTest() throws InterruptedException {
//tapOnPlusButton
        app.event().initEventCreation();
        app.event().tapOnPencilButton();
        Thread.sleep(5000);
        app.event().selectDate("future", "FEBRUARY","10");
        app.event().fillCreationForm("Event", "Work",0, "15");
        //app.event().rangeTime("9","15","12","15");
        app.event().pressAddButton();
        Assert.assertTrue(app.event().isEventPresent());
    }
    //delete_menu
}
