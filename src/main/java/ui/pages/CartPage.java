package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Optional;

public class CartPage extends PageHeader {

    private final By subtotalPriceLabel = By.cssSelector("#sc-subtotal-amount-buybox span");
    private final By subtotalItemsQuantityLabel = By.id("sc-subtotal-label-buybox");
    private final By allItemsProductWebElements = By.xpath("//div[@data-name='Active Items']/div[.//a]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public double getCartSubtotalPrice() {
        String rawPrice = driver.findElement(subtotalPriceLabel).getText();
        return Double.parseDouble(rawPrice);
    }

    @Step
    public int getCartItemsSubtotalQuantity() {
        String rawPrice = driver.findElement(subtotalItemsQuantityLabel).getText();
        String rawInteger = rawPrice.split("(\\d+)")[0];
        return Integer.parseInt(rawInteger);
    }

    @Step
    public CartPage changeProductQuantity(String productId, int quantity) {
        WebElement productToUpdate = driver.findElements(allItemsProductWebElements).stream()
                .filter(element -> element.getAttribute("data-asin").equals(productId))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Product wasn't found"));
        WebElement element = productToUpdate.findElement(By.xpath("//span[@name='quantity']"));
        new Select(element).selectByValue(String.valueOf(quantity));
        return new CartPage(driver);
    }

}
