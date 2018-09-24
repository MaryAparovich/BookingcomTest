package com.epam.aparovich.tests;

import com.epam.aparovich.driver.DriverSingleton;
import com.epam.aparovich.pages.accommodation.MainPageAccomodation;
import com.epam.aparovich.pages.accommodation.UserPage;
import com.epam.aparovich.pages.rentalcars.MainPageRental;
import com.epam.aparovich.pages.rentalcars.SearchResultsRentalCars;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BookingRentalCarTest {
    private WebDriver driver;
    private MainPageAccomodation mainPageAccomodation;
    private UserPage userPage;
    private MainPageRental mainPageRental;
    private SearchResultsRentalCars searchResultsRentalCars;
    private static final String EMAIL = "altprint19@mail.ru";
    private static final String PASSWORD = "marybooking";
    private static final String COUNTRY = "Беларусь";
    private static final String CITY = "Минск";
    private static final int DATE_FROM = 27;
    private static final int DATE_TO = 29;

    @BeforeMethod(groups = {"rental"}, description = "Init browser")
    public void setUp() {
        driver = DriverSingleton.getDriver();
        mainPageAccomodation = new MainPageAccomodation(driver);
        userPage = new UserPage(driver);
        mainPageRental = new MainPageRental(driver);
        searchResultsRentalCars = new SearchResultsRentalCars(driver);
        mainPageAccomodation.openPage();
        mainPageAccomodation.login(EMAIL, PASSWORD);
    }

    @Test(groups = {"rental"})
    public void testCorrectDateDisplay(){
        userPage.goToRentalCar();
        mainPageRental.searchRentalCar(COUNTRY, CITY, DATE_FROM, DATE_TO);
        assertTrue(searchResultsRentalCars.dateEqual(DATE_FROM, DATE_TO));
    }

    @AfterMethod(groups = {"rental"}, description = "Stop Browser")
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
