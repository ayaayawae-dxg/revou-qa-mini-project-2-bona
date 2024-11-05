package com.testing.mobile.pages;

import com.testing.mobile.TestContext;
import com.testing.mobile.components.CartIcon;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPageTest {
  private final AndroidDriver driver;
  private final WebDriverWait wait;
  private final CartIcon cartIcon;
  private final CartPage cartPage;

  public CartPageTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
    this.cartIcon = new CartIcon(driver);
    this.cartPage = new CartPage(driver);
  }

  @Then("User should see the cart items {string} with quantity {int} and color {string}")
  public void userShouldSeeTheCartItemsWithQuantityAndColor(String productName, Integer quantity, String color) {
    Assert.assertEquals(cartPage.getProductName(), productName);
    Assert.assertEquals(cartPage.getQuantity(), quantity);
    Assert.assertEquals(cartPage.getColor(color), color);
  }

  @When("User click on the remove button with product name {string} and color {string}")
  public void userClickOnTheRemoveButtonWithProductNameAndColor(String productName, String color) throws InterruptedException {
    cartPage.clickRemoveButton();
  }

  @Then("User should not see the cart items {string} and color {string}")
  public void userShouldNotSeeTheCartItemsAndColor(String productName, String color) {
    Assert.assertFalse(cartPage.isProductPresent(productName, color));
  }
}
