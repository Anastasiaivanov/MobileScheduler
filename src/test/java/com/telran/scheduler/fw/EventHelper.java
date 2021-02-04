package com.telran.scheduler.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public void rangeTime(int fromHour, int fromMinute, int toHour, int toMinute) {
        tap(By.id("info_range_edit"));
        tap(By.id("hours"));
        int hours = Integer.parseInt(driver.findElement(By.id("hours")).getText());
        while (hours != fromHour) {
            for (int i = 0; i <= fromHour; i++) {
                
            }
        }

        tap(By.id("info_tp_date_to"));
        tap(By.id("hours"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"" + toHour + "\"]\n"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"" + toMinute + "\"]\n"));
        tap(By.id("info_timePicker_ok_btn"));
    }

    public void fillCreationForm(String eventTitle, String eventType, int breaks, String wage) {
        swipeRightToLeft();
        type(By.id("info_title_input"), eventTitle);
        type(By.id("info_type_input"), eventType);
        hideKeyboard();
        if (breaks > 0) {
            for (int i = 0; i < breaks; i++) {
                addBreak();
            }
        }
        editWage(wage);
    }

    public void addBreak() {
        tap(By.id("info_break_plus_btn"));
    }

    public void editWage(String wage) {
        tap(By.id("info_wage_edit"));
        type(By.id("info_wage_input"), wage);
        hideKeyboard();
        tap(By.id("info_wage_save"));
    }

    public void pressAddButton() {
        tap(By.id("info_save_btn"));
    }

    public void selectDate(String type, String month, String dayOfMonth) {
        if (!getSelectedMonth().equals(month)) {
            if (type.equals("past")) {
                swipeToRightUntilNeededMonth(month);
                if (!getSelectedDayOfMonth().equals(dayOfMonth)) {
                    swipeToRightUntilNeededDayOfMonth(dayOfMonth);
                }
            } else if (type.equals("future")) {
                swipeToLeftUntilNeededMonth(month);
                if (!getSelectedDayOfMonth().equals(dayOfMonth)) {
                    swipeToLeftUntilNeededDayOfMonth(dayOfMonth);
                }
            }
        } else if (!getSelectedDayOfMonth().equals(dayOfMonth)) {
            if (type.equals("past")) {
                swipeToRightUntilNeededDayOfMonth(dayOfMonth);
            } else if (type.equals("future")) {
                swipeToLeftUntilNeededDayOfMonth(dayOfMonth);
            }
        }
    }

    private void moveElementToLeft(By locator) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int leftPoint = (int) (size.width * 0.2);
        int rightPoint = (int) (size.width * 0.5);
        int y = size.height / 5;

        WebElement element = driver.findElement(locator);

        action.longPress(PointOption.point(rightPoint, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(leftPoint, y)).release().perform();
    }

    private void swipeToLeftUntilNeededDayOfMonth(String dayOfMonth) {
        while (!getSelectedDayOfMonth().equals(dayOfMonth)) {
            moveElementToLeft(By.id("info_viewPager"));
            getSelectedDayOfMonth();
        }
    }

    private void swipeToLeftUntilNeededMonth(String month) {
        while (!getSelectedMonth().equals(month)) {
            moveElementToLeft(By.id("info_viewPager"));
            getSelectedMonth();
        }
    }

    private void swipeToRightUntilNeededDayOfMonth(String dayOfMonth) {
        while (!getSelectedDayOfMonth().equals(dayOfMonth)) {
            moveElementToRight(By.id("info_viewPager"));
            getSelectedDayOfMonth();
        }
    }

    private String getSelectedDayOfMonth() {
        WebElement selectedDate = driver.findElement(By.id("date_container_layout"));
        return selectedDate.findElement(By.xpath(".//*[@resource-id='com.example.svetlana.scheduler:id/row_day_number_txt']"))
                .getText();
    }

    private void swipeToRightUntilNeededMonth(String month) {
        while (!getSelectedMonth().equals(month)) {
            moveElementToRight(By.id("info_viewPager"));
            getSelectedMonth();
        }
    }

    private void moveElementToRight(By locator) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int leftPoint = (int) (size.width * 0.2);
        int rightPoint = (int) (size.width * 0.8);
        int y = size.height / 5;

        WebElement element = driver.findElement(locator);

        action.longPress(PointOption.point(leftPoint, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(rightPoint, y)).release().perform();
    }

    private String getSelectedMonth() {
        WebElement element = driver.findElement(By.id("date_container_layout"));
        return element.findElement(By.xpath(".//*[@resource-id='com.example.svetlana.scheduler:id/row_month_txt']"))
                .getText();
    }

    public boolean isEventPresent() {
        waitForElement(By.id("row_container_main"), 20);
        return isElementPresent(By.id("row_container_main"));
    }

    public void multiSwipe(int swipsCount) {
        for (int i = 0; i < swipsCount; i++) {
            swipUp();
        }
    }

    private void swipUp() {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height *0.8);
        int endY = (int) (size.height *0.2);

        action.longPress(PointOption.point(x,startY))
                .moveTo(PointOption.point(x,endY)).release().perform();
    }

    public void deleteFirstEvent(){
        tap(By.id("row_day_number_txt"));
        tap(By.id("delete_menu"));
    }
}
