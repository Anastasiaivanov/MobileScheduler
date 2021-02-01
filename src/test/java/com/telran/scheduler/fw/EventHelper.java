package com.telran.scheduler.fw;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class EventHelper extends HelperBase {

    public EventHelper(AppiumDriver driver) {
        super(driver);
    }

    public boolean isIconSortOptionsPresent() {
        return isElementPresent(By.id("sort_options"));
        //By.xpath("//*[@resource-id='com.example.svetlana-scheduler:id/sort_options']")
    }

    public void initEventCreation() {
        tap(By.id("fab_main"));
    }

    public void tapOnPencilButton() {
        tap(By.id("fab_add_event"));
    }

    public void rangeTime(String fromHour, String fromMinute, String toHour, String toMinute) {
        tap(By.id("info_range_edit"));
        tap(By.id("hours"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"" + fromHour + "\"]\n"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"" + fromMinute + "\"]\n"));
        tap(By.id("info_tp_date_to"));
        tap(By.id("hours"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"" + toHour + "\"]\n"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"" + toMinute + "\"]\n"));
        tap(By.id("info_timePicker_ok_btn"));
    }

    public void fillCreationForm(String eventTitle, String eventType) {
        type(By.id("info_title_input"), eventTitle);
        type(By.id("info_type_input"), eventType);
        hideKeyboard();
    }

    public void addOneBreak() {
        tap(By.id("info_break_plus_btn"));
    }

    public void editWage(String amount) {
        tap(By.id("info_wage_edit"));
        type(By.id("info_wage_input"), amount);
        tap(By.id("info_wage_save"));
    }

    public void pressAddButton(){
        tap(By.id("info_save_btn"));
    }
}
