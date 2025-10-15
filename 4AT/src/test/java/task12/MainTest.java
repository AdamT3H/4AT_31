package task12;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.example.task11.DriverProvider;
import org.example.task12.PageFactoryLogIn;
import org.example.task12.PageFactoryMonitorsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//General
//Implement PageFactory for a few pages.
//Implement a Wrapper for common WebElements (choose your variant).
//Implement methods for your web element with console logging. (Choose your variant with specific methods)
//Use those methods in a simple TC scenario

//Variant: 6
//Textareas:
//setText - sets the text value of a textarea
//getText - retrieves the text value of a textarea
//waitForText - waits for a specific text to be present in the textarea



public class MainTest {
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginPage(){
        driver.navigate().to("https://www.demoblaze.com/");

        PageFactoryLogIn logInPage = new PageFactoryLogIn(driver);
        logInPage.logIn("test12", "test12");
    }

    @Test
    public void testMonitorsPage(){
        driver.navigate().to("https://www.demoblaze.com/");

        PageFactoryMonitorsPage monitorsPage = new PageFactoryMonitorsPage(driver);
        monitorsPage.testMonitorToCart();
    }
}
