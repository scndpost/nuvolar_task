package com.amazon;

import configuration.ConfigManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseUiTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @SneakyThrows
    @BeforeMethod
    public void setUp() {
        switch (ConfigManager.config.browser()) {
            case CHROME:
                driver.set(new ChromeDriver());
                break;
            case EDGE:
                driver.set(new EdgeDriver());
                break;
            case FIREFOX:
                driver.set(new FirefoxDriver());
                break;
            default:
                throw new Exception("Wrong browser setup is passed to the configuration file");
        }
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterMethod
    public void cleanUp() {
        if (driver.get() != null)
            driver.get().quit();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
