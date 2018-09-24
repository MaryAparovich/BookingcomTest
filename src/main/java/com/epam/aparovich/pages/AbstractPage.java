package com.epam.aparovich.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected final Logger logger = LogManager.getRootLogger();

    public AbstractPage(WebDriver driver ) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, 50);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }


}
