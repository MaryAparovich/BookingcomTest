package com.epam.aparovich.tests;

import com.epam.aparovich.driver.DriverSingleton;
import com.epam.aparovich.pages.accommodation.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class BookingAccommodationTest {

    private WebDriver driver;
    private MainPageAccomodation mainPageAccomodation;
    private UserPage userPage;
    private AccommodationBookingPage accommodationBookingPage;
    private SearchResultsAccommodationPage searchHotelsPage;
    private FavoritesAccommodationPage favoritesAccommodationPage;
    private static final String EMAIL = "altprint19@mail.ru";
    private static final String PASSWORD = "marybooking";
    private static final String CITY = "Минск";
    private static final int DATE_OF_ARRIVAL = 25;

    @BeforeMethod(groups = {"accomodation"}, description = "Init browser")
    public void setUp() {
        driver = DriverSingleton.getDriver();
        mainPageAccomodation = new MainPageAccomodation(driver);
        userPage = new UserPage(driver);
        accommodationBookingPage = new AccommodationBookingPage(driver);
        searchHotelsPage = new SearchResultsAccommodationPage(driver);
        favoritesAccommodationPage = new FavoritesAccommodationPage(driver);
        mainPageAccomodation.openPage();
        mainPageAccomodation.login(EMAIL, PASSWORD);
    }

    @Test(groups = {"accomodation"})
    public void testFilterByLowestPrices() {
        userPage.chooseAccommodationByCity(CITY, DATE_OF_ARRIVAL);
        searchHotelsPage.applyFilterByAvailableAccommodation();
        searchHotelsPage.applyFilterByLowestPrices();
        List<Integer> listPrice = searchHotelsPage.getListPrices();
        assertTrue(searchHotelsPage.isListInOrderByAsc(listPrice));
    }

    @Test(groups = {"accomodation"})
    public void testFilterByHighestReviews() {
        userPage.chooseAccommodationByCity(CITY, DATE_OF_ARRIVAL);
        searchHotelsPage.applyFilterByAvailableAccommodation();
        searchHotelsPage.applyFilterByHighestReviews();
        List<String> stringReviewList = searchHotelsPage.getListReviews();
        assertTrue(searchHotelsPage.isListReviewsHighest(stringReviewList, 9));
    }

    @Test(groups = {"accomodation"})
    public void testBookAccommodationFlow() {
        userPage.chooseAccommodationByCity(CITY, DATE_OF_ARRIVAL);
        searchHotelsPage.applyFilterByAvailableAccommodation();
        searchHotelsPage.applyFilterByHighestReviews();
        searchHotelsPage.clickOnAvailableAccommodation(1);
        String selectRoomUrl = searchHotelsPage.getCurrentUrl();
        accommodationBookingPage.selectRoomCount();
        String detailBookUrl = accommodationBookingPage.getCurrentUrl();
        assertNotEquals(selectRoomUrl, detailBookUrl);
    }

    @Test(groups = {"accomodation"})
    public void testListOfFavorites() {
        userPage.chooseAccommodationByCity(CITY, DATE_OF_ARRIVAL);
        searchHotelsPage.applyFilterByAvailableAccommodation();
        Integer accommodationId = searchHotelsPage.clickOnAvailableAccommodation(5);
        if (accommodationId != null) {
            accommodationBookingPage.addToListFavorites(accommodationId);
            accommodationBookingPage.goToListFavorites();

            boolean isAccommodationPresent = favoritesAccommodationPage.isAccommodationPresent(accommodationId);
            if (isAccommodationPresent) {
                favoritesAccommodationPage.deleteAccommodation(accommodationId);
            }
            Assert.assertTrue(isAccommodationPresent);
        }
    }

    @AfterMethod(groups = {"accomodation"}, description = "Stop Browser")
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

}
