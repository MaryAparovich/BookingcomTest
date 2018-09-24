package com.epam.aparovich.pages.accommodation;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FavoritesAccommodationPage extends AbstractPage {
    @FindBy(xpath = "//*[@data-tab='main']//*[@data-id]")
    private List<WebElement> favoriteHotelList;

    public FavoritesAccommodationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isAccommodationPresent(int accommodationId) {
        WebElement accommodation = findAccommodationById(accommodationId);
        return accommodation != null;
    }

    public void deleteAccommodation(int accommodationId) {
        WebElement accommodation = findAccommodationById(accommodationId);
        if (accommodation != null) {
            List<WebElement> aTagElements = accommodation.findElements(By.xpath("./a"));
            aTagElements.get(1).click();
            logger.info("Favorite accommodation is deleted");
        }
    }

    private WebElement findAccommodationById(int accommodationId) {
        for (WebElement accommodation : favoriteHotelList) {
            String currentAccommodationIdIdString = accommodation.getAttribute("data-id");
            int currentAccommodationId = Integer.valueOf(currentAccommodationIdIdString);
            if (currentAccommodationId == accommodationId) {
                return accommodation;
            }
        }
        return null;
    }
}
