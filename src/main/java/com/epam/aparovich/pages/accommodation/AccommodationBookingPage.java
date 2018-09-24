package com.epam.aparovich.pages.accommodation;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class AccommodationBookingPage extends AbstractPage {

    @FindBy(className = "hprt-nos-select")
    private WebElement selectRoom;

    @FindBy(id = "hprt-form")
    private WebElement bookForm;

    @FindBy(xpath = "//div[@id='hotel-wishlists']//a")
    private WebElement btnGoToFavorites;


    public AccommodationBookingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void selectRoomCount() {
        Select dropdown = new Select(selectRoom);
        dropdown.selectByIndex(1);
        bookForm.submit();
    }

    public void addToListFavorites(int hotelId) {

        WebElement favoritesButton = driver.findElement(By.xpath("//div[@id='right']//form/*[@data-hotel-id='" + hotelId + "']"));
            favoritesButton.click();
        logger.info("Add to list of favorites");

    }

    public void goToListFavorites() {
            btnGoToFavorites.click();
            logger.info("Go to list favorites");
            driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(driver.getWindowHandles().size()-1));
    }
}
