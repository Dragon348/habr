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
        searchButton = driver.findElement(By.id("search-form-btn"));
        searchButton.click();
        searchField = driver.findElement(By.id("search-form-field"));
        searchField.sendKeys("Linux");
        searchField.submit();
        flowList = driver.findElement(By.id("flow"));
        Select select = new Select(flowList);
        select.selectByVisibleText("Разработка");
        flowList.submit();
        orderByDateButton = driver.findElementByXPath("//a[contains(@href, 'order_by=date')]");
        orderByDateButton.click();
        List<WebElement> articleList = driver.findElementsByXPath("//h2[@class='post__title']//a[1]");
        HashMap<String, Integer> articles = new HashMap<String, Integer>();
        for (WebElement articleHead:articleList) {
            String path = "//a[@href=" + "'" + articleHead.getAttribute("href") + "#comments" + "'"  + ']' ;
            WebElement comment = driver.findElementByXPath(path);
            int commentCount;
            try {
                commentCount = Integer.parseInt(comment.getText());
            }
            catch (Throwable t) {
                commentCount = 0;
            }
            articles.put(articleHead.getAttribute("href"), commentCount);
        }

        articles.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).forEach(System.out::println);
    }

}

