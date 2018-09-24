package com.epam.aparovich.tests;

import com.epam.aparovich.driver.DriverSingleton;
import com.epam.aparovich.pages.accommodation.MainPageAccomodation;
import com.epam.aparovich.pages.accommodation.UserPage;
import com.epam.aparovich.pages.taxi.DetailsPassengerPage;
import com.epam.aparovich.pages.taxi.MainPageTaxi;
import com.epam.aparovich.pages.taxi.SearchResultsTaxiPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class BookingTaxiTest {
    private WebDriver driver;
    private MainPageAccomodation mainPageAccomodation;
    private UserPage userPage;
    private MainPageTaxi mainPageTaxi;
    private SearchResultsTaxiPage searchResultsTaxiPage;
    private DetailsPassengerPage detailsPassengerPage;
    private static final String NAME = "Maria";
    private static final String LASTNAME = "Aparovich";
    private static final String PHONE_NUMBER = "+2414523699";
    private static final String EMAIL = "altprint19@mail.ru";
    private static final String PASSWORD = "marybooking";
    private static final String FROM = "Тверская";
    private static final String TO = "Шереметьево";
    private static final int COUNT_PASSENGER = 3;


    @BeforeMethod(groups = {"taxi"}, description = "Init browser")
    public void setUp() {
        driver = DriverSingleton.getDriver();
        mainPageAccomodation = new MainPageAccomodation(driver);
        mainPageTaxi = new MainPageTaxi(driver);
        userPage = new UserPage(driver);
        detailsPassengerPage = new DetailsPassengerPage(driver);
        searchResultsTaxiPage = new SearchResultsTaxiPage(driver);
        mainPageAccomodation.openPage();
        mainPageAccomodation.login(EMAIL, PASSWORD);
        userPage.goToTaxi();
    }

    @Test(groups = {"taxi"})
    public void testAvailabilityOfTaxiReservations() {
        mainPageTaxi.searchTaxi(FROM, TO, COUNT_PASSENGER);
        assertTrue(searchResultsTaxiPage.isTaxiFound());
    }

    @Test(groups = {"taxi"})
    public void testBookTaxiFlowCardValidation()  {
        mainPageTaxi.searchTaxi(FROM, TO, COUNT_PASSENGER);
        searchResultsTaxiPage.clickOnBookTaxi();
        detailsPassengerPage.fillDetailsPassenger(NAME, LASTNAME, EMAIL, PHONE_NUMBER);
        int errorCount = detailsPassengerPage.getErrorCount();
        assertEquals(errorCount, 3);
    }

    @AfterMethod(groups = {"taxi"}, description = "Stop Browser")
    public void stopBrowser() {
       DriverSingleton.closeDriver();
    }
}

