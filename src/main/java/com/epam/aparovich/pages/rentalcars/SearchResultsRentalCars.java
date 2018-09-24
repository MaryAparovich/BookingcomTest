package com.epam.aparovich.pages.rentalcars;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsRentalCars extends AbstractPage {

    @FindBy(xpath = "//*[@class='ab-SearchSummary_GridFix-2 ab-SearchSummary_PickUp-day']")
    private WebElement dateFrom;

    @FindBy(xpath = "//*[@class='ab-SearchSummary_GridFix-8 ab-SearchSummary_DropOff-day ab-SearchSummary_DropOff']")
    private WebElement dateTo;

    public SearchResultsRentalCars(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean dateEqual(int dateFrom, int dateTo) {

        if ((Integer.valueOf(this.dateFrom.getText()) == dateFrom) && (Integer.valueOf(this.dateTo.getText()) == dateTo)) {
            return true;
        }
        return false;
    }
}
