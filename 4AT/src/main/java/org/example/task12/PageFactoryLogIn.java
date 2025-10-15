package org.example.task12;

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
    WebElement logInButton;

    @FindBy(id = "loginusername")
    WebElement usernameField;

    @FindBy(id = "loginpassword")
    WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement confirmLoginButton;

    TextAreasWrapper usernameInput;
    TextAreasWrapper passwordInput;

    public PageFactoryLogIn(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        usernameInput = new TextAreasWrapper(driver, usernameField, "setTextTestUserName");
        passwordInput = new TextAreasWrapper(driver, passwordField, "setTextTestPassword");
    }

    public void logIn(String username, String password) {
        logInButton.click();

        try { Thread.sleep(1000); } catch (InterruptedException e) { }

        usernameInput.setText(username);
        passwordInput.setText(password);

        confirmLoginButton.click();
    }
}
