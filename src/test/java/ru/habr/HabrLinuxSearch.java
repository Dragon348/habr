package ru.habr;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class HabrLinuxSearch extends SeleniumProperties{
    @Test
    public void linuxSearch() {

        driver.get("https://habr.com/ru/");
        WebElement element;
        element = driver.findElement(By.id("search-form-btn"));
        element.click();
        element = driver.findElement(By.id("search-form-field"));
        element.sendKeys("Linux");
        element.submit();
        element = driver.findElement(By.id("flow"));
        Select select = new Select(element);
        select.selectByVisibleText("Разработка");
        element.submit();
        List<WebElement> elements = driver.findElementsByXPath("//a[contains(@href,'/blog/')]");
        for (WebElement element1:elements) {
            System.out.println(element1.getText());
        }
    }
}
