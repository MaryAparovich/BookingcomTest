package com.epam.aparovich.pages.accommodation;

import com.epam.aparovich.pages.AbstractPage;
import com.epam.aparovich.utils.ConverterUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsAccommodationPage extends AbstractPage {

    @FindBy(xpath = "//table/tbody/tr/td[2]//strong/b")
    private List<WebElement> priceLabelList;

    @FindBy(linkText = "Самая низкая цена в начале")
    private WebElement filterByLowestPrices;

    @FindBy(linkText = "Показать только доступные варианты")
    private WebElement filterByAvailableAccommodation ;

    @FindBy(xpath = "//div[@id='filter_review']//a[1]")
    private WebElement filterByHighestReviews;

    @FindBy(xpath = "//div[@class='bui-review-score__badge']")
    private List<WebElement> reviewBadgeList;

    @FindBy(xpath = "//*[@data-hotelid]")
    private List<WebElement> availableAccommodationList;

    @FindBy(xpath = "//*[@data-hotelid]//span[@class='b-button__text']")
    private List<WebElement> availableAccommodationBtnList;

    public SearchResultsAccommodationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void applyFilterByAvailableAccommodation () {
        filterByAvailableAccommodation.click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("sr-usp-overlay__loading")));
        logger.info("Filter by available accommodation is applied");
    }

    public void applyFilterByLowestPrices() {
        filterByLowestPrices.click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("sr-usp-overlay__loading")));
        logger.info("Filter by lowest prices is applied");
    }

    public List<Integer> getListPrices() {
        List<WebElement> webElementList = priceLabelList;
        List<Integer> listPrice = new ArrayList<>();

        cutNumbers(webElementList, listPrice);
        return listPrice;
    }

    private void cutNumbers(List<WebElement> webElementList, List<Integer> listPrice) {
        for (int i = 0; i < webElementList.size(); i++) {
            String priceStr = webElementList.get(i).getText();
            String[] priceArr = priceStr.split(" ");
            int price = Integer.valueOf(priceArr[1]);
            listPrice.add(price);
        }
    }

    public boolean isListInOrderByAsc(List<Integer> listPrice) {
        for (int i = 0; i < listPrice.size() - 1; i++) {
            if (listPrice.get(i) > listPrice.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public void applyFilterByHighestReviews() {
        filterByHighestReviews.click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("sr-usp-overlay__loading")));
        logger.info("Filter by highest reviews is applied");
    }

    public List<String> getListReviews() {
        List<WebElement> webElementList = reviewBadgeList;
        List<String> stringReviewList = new ArrayList<>();

        for (int i = 0; i < webElementList.size(); i++) {
            stringReviewList.add(webElementList.get(i).getText());
        }
        return stringReviewList;

    }

    public boolean isListReviewsHighest(List<String> stringReviewList, int threshold) {
        for (int i = 0; i < stringReviewList.size(); i++) {
            if (ConverterUtils.stringToDouble(stringReviewList.get(i)) < threshold) {
                return false;
            }
        }
        return true;
    }

    public Integer clickOnAvailableAccommodation (int index)  {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("sr-usp-overlay__loading")));
        if (index < availableAccommodationBtnList.size()) {
            WebElement accommodation = availableAccommodationList.get(index);
            String accommodationIdString = accommodation.getAttribute("data-hotelid");

            availableAccommodationBtnList.get(index).click();
            logger.info("Click on available accommodation");

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            return Integer.valueOf(accommodationIdString);
        }
        return null;
    }
}
