package ru.habr;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;


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
        driver.findElementByXPath("//a[contains(@href, 'order_by=date')]").click();
        List<WebElement> elements = driver.findElementsByXPath("//h2[@class='post__title']//a[1]");
        HashMap<String, Integer> articles = new HashMap<String, Integer>();
        for (WebElement element1:elements) {
            System.out.println(element1.getAttribute("href"));
            String path = "//a[@href=" + "'" + element1.getAttribute("href") + "#comments" + "'"  + ']' ;
            WebElement comment = driver.findElementByXPath(path);

            System.out.println(comment.getText());
            int commentCount;
            try {
                commentCount = Integer.parseInt(comment.getText());
            }
            catch (Throwable t) {
                commentCount = 0;
            }
            articles.put(element1.getAttribute("href"), commentCount);
            }

        articles.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).forEach(System.out::println);
    }

}

