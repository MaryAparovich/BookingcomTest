package com.epam.aparovich.pages.accommodation;

import com.epam.aparovich.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;

public class MainPageAccomodation extends AbstractPage {

    private final String BASE_URL = "https://booking.com/";

    @FindBy(xpath = "//div[@class='sign_in_wrapper']")
    private WebElement loginButton;

    @FindBy(name = "username")
    private WebElement inputName;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(xpath = "//div[@role='tab'][1]")
    private WebElement loginTab;

    public MainPageAccomodation(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Main page is opened");
    }

    public void login(String email, String password) {
        loginButton.click();
        loginTab.click();
        inputName.sendKeys(email);
        inputPassword.sendKeys(password);
        inputPassword.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("sr-usp-overlay__loading")));
    }
}
