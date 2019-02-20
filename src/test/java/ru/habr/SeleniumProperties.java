package ru.habr;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumProperties {
    public WebElement searchButton;
    public WebElement searchField;
    public WebElement flowList;
    public WebElement orderByDateButton;
    public ChromeDriver driver;
    @Before
    public void setConfig() {
        System.setProperty("webdriver.chrome.driver", "F:\\Projects\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @After
    public void afterTest() {
        driver.quit();
    }
}
