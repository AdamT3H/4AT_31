package task11;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.example.task11.DriverProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class mainTest {

    @BeforeTest
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        DriverProvider.getDriver().manage().window().maximize();
    }

    @Test
    public void main(){
        WebDriver driver = DriverProvider.getDriver();
        // Step 1: Click Log In button
            SignUpPage signUpPage = new SignUpPage();
            signUpPage.clickSignUp();
        // Step 2: Fill the form
            driver.findElement(By.id("loginusername")).sendKeys(UUID.randomUUID().toString().substring(0, 10));
            driver.findElement(By.id("loginpassword")).sendKeys(UUID.randomUUID().toString().substring(0, 10));
        // Step 3: Submit
            driver.findElement(By.xpath("//button[text() = 'Log in']")).click();
        // Step 4: Validate success message
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            String message = alert.getText();
            Assert.assertEquals(message, "User does not exist.", "Invalid message");

        // Step 5: Close message
            alert.accept();
    }

    @AfterTest
    public void tearDown(){
        DriverProvider.getDriver().close();
    }
}
