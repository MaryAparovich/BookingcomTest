package com.epam.aparovich.pages.taxi;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DetailsPassengerPage extends AbstractPage {

    @FindBy (className = "rw-form__select")
    WebElement selectAddress;

    @FindBy (id = "firstname")
    WebElement name;

    @FindBy (id = "lastname")
    WebElement lastname;

    @FindBy (id = "emailaddress")
    WebElement emailAddress;

    @FindBy (id = "verifyemailaddress")
    WebElement verifyEmailAddress;

    @FindBy (id = "contactNumber")
    WebElement contactNumber;

    @FindBy (xpath = "//div[@class='rw-form__submit']/*[@class='rw-tt-translation-text']")
    WebElement continueButton;

    @FindBy (xpath = "//button[@class='rw-btn__primary rw-btn--inline rw-payment-details__btn']")
    WebElement bookButton;


    public DetailsPassengerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void fillDetailsPassenger(String name, String lastname, String email, String phoneNumber) {
        selectAddress();
        this.name.sendKeys(name);
        this.lastname.sendKeys(lastname);
        emailAddress.sendKeys(email);
        verifyEmailAddress.sendKeys(email);
        contactNumber.clear();
        contactNumber.sendKeys(phoneNumber);
        continueButton.click();
        driverWait.until(ExpectedConditions.elementToBeClickable(bookButton)).click();
        logger.info("Booking of taxi is clicked");
    }

    public void selectAddress()  {
        Select dropdown = new Select(selectAddress);
        dropdown.selectByValue("Mrs");
    }

    public int getErrorCount() {
        List<WebElement> errorLabelList = driver.findElements(By.xpath("//*[contains(@class, 'rw-form__input--invalid')]"));
        if (errorLabelList != null) {
            return errorLabelList.size();
        } else {
            return 0;
        }
    }




}
