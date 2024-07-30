package ui.pages;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends PageHeader {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://www.amazon.com/");
    }

}
