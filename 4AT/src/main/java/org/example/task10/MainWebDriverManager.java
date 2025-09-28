package org.example.task10;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainWebDriverManager {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");

        ChromeDriverManager.getInstance().setup();

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.demoblaze.com/");

        WebElement logInMenuButton = driver.findElement(By.xpath("//a[contains(normalize-space(.), \"Log in\")]"));
        WebElement usernameInput = driver.findElement(By.id("loginusername"));
        WebElement logInButton = driver.findElement(By.xpath("//button[@onclick='logIn()']"));

        logInMenuButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        usernameInput.sendKeys("userNameTest");
        logInButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }
}
