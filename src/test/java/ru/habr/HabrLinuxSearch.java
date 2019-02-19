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
        List<WebElement> elements = driver.findElementsByXPath("//h2[@class='post__title']//a[1]");
        //ul[@id='infopanel_post_440810']//span[contains(@title,'Читать комментарии')][contains(text(),'5')]
                                                                     //  /html[1]/body[1]/div[1]/div[3]/div[1]/section[1]/div[1]/div[3]/ul[1]/li[3]/article[1]/h2[1]/a[1]
        for (WebElement element1:elements) {
            System.out.println(element1.getAttribute("href"));
            String path = '"' + "//a[@href=" + "'" + element1.getAttribute("href") + "#comments" + "'"  + ']' + '"';
            WebElement comment = driver.findElement(By.xpath(path));
            System.out.println(element1.getAttribute(comment.getText()));
        }
    }
}
