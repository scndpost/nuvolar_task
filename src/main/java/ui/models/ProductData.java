package ui.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Data
@AllArgsConstructor
public class ProductData {

    private Double price;
    private String id;

    public static ProductData mapToProductData(WebElement webElement) {
        String rawPrice = webElement.findElement(By.cssSelector("a span span.a-offscreen")).getAttribute("textContent")
                .replace("$", "");
        String id = webElement.getAttribute("data-asin");

        double price = Double.parseDouble(rawPrice);
        return new ProductData(price, id);
    }
}
