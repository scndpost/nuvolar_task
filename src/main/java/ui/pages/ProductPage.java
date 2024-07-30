package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends PageHeader {

    private final By quantityDropdown = By.xpath("//select[@name='quantity']");
    private final By addToCartButton = By.id("add-to-cart-button");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public ProductAddedToCartPage clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
        return new ProductAddedToCartPage(driver);
    }

    @Step
    public ProductPage setQuantityDropdown(int quantity) {
        WebElement element = driver.findElement(quantityDropdown);
        new Select(element).selectByValue(String.valueOf(quantity));
        driver.findElement(By.id("productTitle")).click();
        return this;
    }

}
