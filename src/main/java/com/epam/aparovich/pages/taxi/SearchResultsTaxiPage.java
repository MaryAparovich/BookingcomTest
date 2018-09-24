package com.epam.aparovich.pages.taxi;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsTaxiPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='rw-search-result']")
    private List<WebElement> foundTaxiList;

    @FindBy(xpath = "//a[@class='rw-btn__primary rw-btn--sentenceCase']")
    private List<WebElement> bookTaxiBtnList;

    public SearchResultsTaxiPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isTaxiFound() {
        if (foundTaxiList.size() > 0) {
            return true;
        }
        return false;
    }

    public void clickOnBookTaxi() {
        bookTaxiBtnList.get(0).click();
        logger.info("Taxi for book is opened");

    }
}
