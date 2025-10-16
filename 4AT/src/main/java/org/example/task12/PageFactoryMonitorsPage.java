package org.example.task12;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageFactoryMonitorsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(linkText = "Monitors")
    WebElement toMonitorsPageButton;

    @FindBy(xpath = "(//div[@id='tbodyid']//h4[@class='card-title']/a)[1]")
    WebElement firstProductName;

    @FindBy(linkText = "Add to cart")
    WebElement addToCartButton;

    public PageFactoryMonitorsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step
    public void testMonitorToCart() {
        toMonitorsPageButton.click();

        wait.until(ExpectedConditions.visibilityOf(firstProductName));
        firstProductName.click();

        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
    }
}

