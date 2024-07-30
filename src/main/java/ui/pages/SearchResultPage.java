package ui.pages;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.models.ProductData;

import java.util.List;

public class SearchResultPage extends PageHeader {

    private final By searchInput = By.xpath("//div[@data-component-type='s-search-result']");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public ProductPage openProductById(String productId) {
        WebElement firstElement = driver.findElements(searchInput).stream()
                .filter(product -> product.getAttribute("data-asin").equals(productId))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No elements were found"));
        firstElement.click();
        return new ProductPage(driver);
    }

    @SneakyThrows
    @Step
    public List<ProductData> collectSearchedItems() {
        return driver.findElements(searchInput).stream()
                .filter(element -> !element.getAttribute("clientHeight").equals("0"))
                .map(ProductData::mapToProductData)
                .toList();
    }

}
