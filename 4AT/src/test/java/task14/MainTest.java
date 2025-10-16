package task14;

import com.automation.remarks.video.annotations.Video;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.example.task12.PageFactoryLogIn;
import org.example.task12.PageFactoryMonitorsPage;
import org.example.task13.CustomListener;
import org.example.task14.CustomAllureListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({CustomListener.class, CustomAllureListener.class})
public class MainTest {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Video(name = "login_test_video")
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

    @Test
    public void testMonitorsPageFAIL(){
        driver.navigate().to("https://www.demoblaze.com/");

        PageFactoryMonitorsPage monitorsPage = new PageFactoryMonitorsPage(driver);
        monitorsPage.testMonitorToCart();

        Assert.fail("Перевірка Allure");
    }

    @AfterClass
    void tearDown(){ driver.quit();}
}
