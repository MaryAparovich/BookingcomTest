package com.epam.aparovich.pages.taxi;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class MainPageTaxi extends AbstractPage {

    @FindBy(id = "pickupLocation")
    private WebElement inputPickupLocation;

    @FindBy(xpath = "//*[@role='listbox']/*[@role='option'][1]")
    private WebElement selectionItem;

    @FindBy(id = "dropoffLocation")
    private WebElement inputDropoffLocation;

    @FindBy(id = "passengers")
    private WebElement selectPassenger;

    @FindBy(name = "searchButton")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='cookies-policy']//button")
    private WebElement closeCookiesButton;

    public MainPageTaxi(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void searchTaxi(String from, String to, int countPassenger) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        inputPickupLocation.sendKeys(from);
        selectionItem.click();
        inputDropoffLocation.sendKeys(to);
        selectionItem.click();
        selectPassengerCount(countPassenger);
    }

    public void selectPassengerCount(int countPassenger) {
        Select dropdown = new Select(selectPassenger);
        dropdown.selectByIndex(countPassenger);
        closeCookiesButton.click();
        searchButton.click();
        logger.info("Search button is clicked");
    }
}
