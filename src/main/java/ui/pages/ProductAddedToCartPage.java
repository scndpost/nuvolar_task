package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductAddedToCartPage extends PageHeader {

    private final By integerNumberPrice = By.cssSelector("#sw-atc-buy-box .a-price-whole");
    private final By fractionalNumberPrice = By.cssSelector("#sw-atc-buy-box .a-price-fraction");
    private final By proceedToCheckoutLabel = By.cssSelector("#sc-buy-box-ptc-button-announce .sc-without-multicart");
    private final By goToCartButton = By.id("sw-gtc");

    public ProductAddedToCartPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public double getCartSubtotalPrice() {
        String integerPrice = driver.findElement(integerNumberPrice).getText();
        String fractionalPrice = driver.findElement(fractionalNumberPrice).getText();
        return Double.parseDouble(integerPrice + fractionalPrice);
    }

    @Step
    public int getTotalItemsInTheCartAmount() {
        String rawAmount = driver.findElement(proceedToCheckoutLabel).getText();
        String stringAmount = rawAmount.split("(\\d+)")[0];
        return Integer.parseInt(stringAmount);
    }

    @Step
    public CartPage clickGoToCartButton() {
        driver.findElement(goToCartButton).click();
        return new CartPage(driver);
    }


}
