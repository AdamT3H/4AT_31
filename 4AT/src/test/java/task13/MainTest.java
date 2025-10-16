package task13;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.example.task12.PageFactoryLogIn;
import org.example.task12.PageFactoryMonitorsPage;
import org.example.task13.CustomListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automation.remarks.video.annotations.Video;


//V6. ITestListener method task (3, 4); ISuiteListener, IExecutionListener,IInvoke...(1, 4)

@Listeners(CustomListener.class)
public class MainTest {

    WebDriver driver;

    @BeforeTest
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
}
