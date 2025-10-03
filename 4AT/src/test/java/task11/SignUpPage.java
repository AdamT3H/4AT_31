package task11;

import org.example.task11.DriverProvider;
import org.openqa.selenium.By;

public class SignUpPage {
    public SignUpPage() {
        DriverProvider.getDriver().get("https://www.demoblaze.com/");
    }

    public void clickSignUp() {
        DriverProvider.getDriver().findElement(By.linkText("Log in")).click();
    }
}
