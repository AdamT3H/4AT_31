package task10;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainTest {
    WebDriver driver;

    @BeforeTest
    void setup(){
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");

        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver(options);
    }

    @Test
    void task10Test(){

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

        Assert.assertTrue(logInMenuButton.isDisplayed());
        Assert.assertTrue(usernameInput.isDisplayed());
        Assert.assertTrue(logInButton.isDisplayed());

        logInButton.click();



        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    void close(){
        driver.quit();
    }
}
