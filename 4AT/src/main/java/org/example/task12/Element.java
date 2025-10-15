package org.example.task12;

import org.openqa.selenium.WebElement;

public class Element {
    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public void click() {
        System.out.println("[Element] click()");
        webElement.click();
    }
}