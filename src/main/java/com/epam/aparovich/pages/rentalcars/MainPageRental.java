package com.epam.aparovich.pages.rentalcars;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class MainPageRental extends AbstractPage {

    @FindBy(id = "pu-country")
    WebElement selectCountry;

    @FindBy(id = "pu-city")
    WebElement selectCity;

    @FindBy(id = "pu-location")
    WebElement selectLocation;

    @FindBy(xpath = "//button/*[@data-ga-action='Closed cookie bar']")
    WebElement closeCookieBanner;

    @FindBy(xpath = "//span[@class='date-panel date-panel--pu']")
    WebElement calendar;

    @FindBy(xpath = "//*[@id='btn-fieldset']/input")
    private WebElement searchCarsButton;


    public MainPageRental(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void searchRentalCar(String country, String city, int dateFrom, int dateTo) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        selectCountry(country);
        selectCity(city);
        closeCookieBanner.click();
        selectLocation();
        calendar.click();
        driverWait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//a[@class='ui-state-default' and contains(text(),'" + dateFrom + "')]"))))
                .click();
        logger.info("DateFrom is selected");
        driverWait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//a[@class='ui-state-default' and contains(text(),'" + dateTo + "')]"))))
                .click();
        logger.info("DateTo is selected");
        driverWait.until(ExpectedConditions.elementToBeClickable(searchCarsButton)).click();
        }

    public void selectCountry(String country) {
        Select dropdown = new Select(selectCountry);
        dropdown.selectByValue(country);
    }

    public void selectCity(String city) {
        Select dropdown = new Select(selectCity);
        dropdown.selectByValue(city);
    }

    public void selectLocation() {
        Select dropdown = new Select(selectLocation);
        dropdown.selectByIndex(2);
    }
}
