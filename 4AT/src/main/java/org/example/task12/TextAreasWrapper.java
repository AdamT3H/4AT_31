//package org.example.task12;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class TextAreasWrapper {
//    WebDriver driver;
//    WebElement element;
//    String name;
//
//    public TextAreasWrapper(WebDriver driver, WebElement element, String name){
//        this.driver = driver;
//        this.element = element;
//        this.name = name;
//    }
//
//    public void setText(String text){
//        System.out.println("[" + name + "] setText: " + text);
//        element.clear();
//        element.sendKeys(text);
//    }
//
//    public String getText(){
//        String text = element.getAttribute("value");
//        System.out.println("[" + name + "] getText: " + text);
//        return text;
//    }
//
//    public void waitForText(String expectedText, int timeoutSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
//        wait.until(ExpectedConditions.textToBePresentInElementValue(element, expectedText));
//    }
//
//}

package org.example.task12;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextAreasWrapper extends Element {

    public TextAreasWrapper(WebElement webElement) {
        super(webElement);
    }

    public void setText(String text) {
        System.out.println("[TextArea] setText: " + text);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public String getText() {
        String text = webElement.getAttribute("value");
        System.out.println("[TextArea] getText: " + text);
        return text;
    }

    public void waitForText(WebDriverWait wait, String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, expectedText));
    }
}
