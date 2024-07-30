package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class PageHeader extends BasePage {

    private final By searchInput = By.id("twotabsearchtextbox");
    private final By searchButton = By.id("nav-search-submit-button");

    public PageHeader(WebDriver driver) {
        super(driver);
    }

    @Step
    public PageHeader insertInSearchArea(String search) {
        driver.findElement(searchInput).sendKeys(search);
        return this;
    }

    @Step
    public SearchResultPage clickSearchButton() {
        driver.findElement(searchButton).click();
        return new SearchResultPage(driver);
    }

}
