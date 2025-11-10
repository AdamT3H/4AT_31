package task20;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Task20Test{
    @Test
    void task20Test() throws MalformedURLException {
        File apkFile = new File("apk/calc.apk");
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Pixel_8")
                .setApp(apkFile.getAbsolutePath());
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.findElement(By.id("com.motorola.cn.calculator:id/formula_or_result"))
                .sendKeys("999999999999999999999");

        driver.findElement(By.id("com.motorola.cn.calculator:id/eq")).click();

        driver.quit();
    }
}
