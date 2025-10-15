package org.example.task12;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;

public class MyFieldDecorator extends DefaultFieldDecorator {
    public MyFieldDecorator(SearchContext context) {
        super(new DefaultElementLocatorFactory(context));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) return null;

            WebElement proxy = proxyForLocator(loader, locator);
            return createInstance(decoratableClass, proxy);
        }
        return super.decorate(loader, field);
    }

    private Class<?> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        try {
            clazz.getConstructor(WebElement.class);
        } catch (Exception e) {
            return null;
        }
        return clazz;
    }

    private Object createInstance(Class<?> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).newInstance(element);
        } catch (Exception e) {
            throw new AssertionError("Cannot create instance of " + clazz, e);
        }
    }
}
