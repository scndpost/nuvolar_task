package com.amazon.tests;

import com.amazon.BaseUiTest;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import ui.pages.CartPage;
import ui.pages.HomePage;

public class AmazonTests extends BaseUiTest {

    @Test(description = "Verify adding product in the cart and updating them recalculates total item quantity and price correctly")
    public void addProductInCartAndUpdateThemTest() {
        int expectedMenHatsQuantity = 2;
        int expectedWomenHatsQuantity = 1;
        int updatedMenHatsQuantity = 1;

        // Add first man hat in the cart and verify price and quantity
        var menHatsSearchResultPage = new HomePage(driver.get())
                // In case captcha appearance need to pass it manually
                .insertInSearchArea("hats for men")
                .clickSearchButton();
        var firstMenHat = menHatsSearchResultPage
                //todo this step fails due to it unable to collect special attributes from all elements.
                .collectSearchedItems()
                .get(0);
        var menHatAddedToCartPage = menHatsSearchResultPage
                .openProductById(firstMenHat.getId())
                .setQuantityDropdown(expectedMenHatsQuantity)
                .clickAddToCartButton();
        double actualCartSubtotalPrice = menHatAddedToCartPage
                .getCartSubtotalPrice();
        int actualCartItemsQuantity = menHatAddedToCartPage
                .getTotalItemsInTheCartAmount();

        double actualMenHatPrice = firstMenHat.getPrice();
        double expectedMenHatsTotalPrice = actualMenHatPrice * expectedMenHatsQuantity;
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualCartItemsQuantity)
                    .as("Verify cart items quantity")
                    .isEqualTo(expectedMenHatsQuantity);
            softly.assertThat(actualCartSubtotalPrice)
                    .as("Verify cart items total price")
                    .isEqualTo(expectedMenHatsTotalPrice);
        });

        // Add first women hat in the cart and verify price and quantity
        var womenHatsSearchResultPage = menHatAddedToCartPage
                .insertInSearchArea("hats for women")
                .clickSearchButton();
        var firstWomenHat = menHatsSearchResultPage
                .collectSearchedItems()
                .get(0);
        var womenHatAddedToCartPage = womenHatsSearchResultPage
                .openProductById(firstWomenHat.getId())
                .setQuantityDropdown(expectedWomenHatsQuantity)
                .clickAddToCartButton();
        double updatedCartSubtotalPrice = womenHatAddedToCartPage
                .getCartSubtotalPrice();
        int updatedCartItemsQuantity = womenHatAddedToCartPage
                .getTotalItemsInTheCartAmount();

        double expectedWomenHatsTotalPrice = firstWomenHat.getPrice() * expectedWomenHatsQuantity;
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedCartItemsQuantity)
                    .as("Verify cart items quantity")
                    .isEqualTo(expectedMenHatsQuantity + expectedWomenHatsQuantity);
            softly.assertThat(updatedCartSubtotalPrice)
                    .as("Verify cart items total price")
                    .isEqualTo(expectedWomenHatsTotalPrice);
        });

        // Update men hat quantity and verify price and quantity
        CartPage cartPage = womenHatAddedToCartPage
                .clickGoToCartButton()
                .changeProductQuantity(firstMenHat.getId(), updatedMenHatsQuantity);


        double expectedHatsTotalPrice = expectedWomenHatsTotalPrice + updatedMenHatsQuantity * firstMenHat.getPrice();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(cartPage.getCartItemsSubtotalQuantity())
                    .as("Verify cart items quantity")
                    .isEqualTo(updatedMenHatsQuantity + expectedWomenHatsQuantity);
            softly.assertThat(cartPage.getCartSubtotalPrice())
                    .as("Verify cart items total price")
                    .isEqualTo(expectedHatsTotalPrice);
        });
    }

}
