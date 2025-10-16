package org.example.task12;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageFactoryLogIn {
    WebDriver driver;

    @FindBy(linkText = "Log in")
    Element logInButton;

    @FindBy(id = "loginusername")
    TextAreasWrapper usernameField;

    @FindBy(id = "loginpassword")
    TextAreasWrapper passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    Element confirmLoginButton;

    public PageFactoryLogIn(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new MyFieldDecorator(driver), this);
    }

    @Step
    public void logIn(String username, String password) {
        logInButton.click();

        try { Thread.sleep(1000); } catch (InterruptedException e) { }

        usernameField.setText(username);
        passwordField.setText(password);

        confirmLoginButton.click();
    }
}
