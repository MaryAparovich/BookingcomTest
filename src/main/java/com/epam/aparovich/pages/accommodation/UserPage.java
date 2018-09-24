package com.epam.aparovich.pages.accommodation;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage extends AbstractPage {

    @FindBy(id = "ss")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class='sb-date-field b-datepicker'][@data-mode='checkout']")
    private WebElement calendarCheckout;

    @FindBy(xpath = "//table/tbody/tr/td[@data-id='1538092800000']")
    private WebElement dateTo;

    @FindBy (xpath = "//div[@class='cross-product-bar__wrapper ']/a[3]")
    private WebElement linkToTaxi;

    @FindBy (xpath = "//div[@class='cross-product-bar__wrapper ']/a[2]")
    private WebElement linkToRentalCar;

    public UserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void chooseAccommodationByCity(String city, int dateFrom) {
        searchField.clear();
        searchField.sendKeys(city);
        searchField.submit();
        searchAccommodationByDate(dateFrom);
    }

    public void searchAccommodationByDate(int dateFrom) {
        driver.findElement(By.xpath("//table/tbody/tr/td/span[text() = '" + dateFrom + "']")).click();
        searchField.submit();
    }

    public void goToTaxi(){
        linkToTaxi.click();
    }

    public void goToRentalCar(){
        linkToRentalCar.click();
        logger.info("Go to rental cars");
    }
}
