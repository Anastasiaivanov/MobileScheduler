package com.telran.scheduler.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCreationTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
        if(!app.event().isIconSortOptionsPresent()){
            app.user().login("ana@gmail.com", "Ana123456");
        }
    }

    @Test
    public void eventCreationTest(){
//tapOnPlusButton
        app.event().initEventCreation();
        app.event().tapOnPencilButton();
        app.event().fillCreationForm("Event", "Work");
        //app.event().rangeTime("9","15","12","15");
        app.event().addOneBreak();
        app.event().addOneBreak();
        app.event().editWage("15");
        app.event().pressAddButton();
    }

}
