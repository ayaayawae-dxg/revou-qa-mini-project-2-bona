package com.testing.web.pages;

import com.testing.web.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class CartPageTest {
  private final RemoteWebDriver driver;
  private final CartPage cartPage;

  public CartPageTest() {
    this.driver = TestContext.getDriver();
    this.cartPage = new CartPage(driver);
  }

  @Then("User will be redirected to the cart page")
  public void userWillBeRedirectedToTheCartPage() {
    Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/cart.html"), "User should be redirected to the cart page");
  }

  @Then("User can see the {string} item in the cart")
  public void userCanSeeTheItemInTheCart(String itemName) {
    Assert.assertTrue(cartPage.isItemInCart(itemName), "User should see the " + itemName + " item in the cart");
  }

  @Then("User should not see the {string} item in the cart")
  public void userShouldNotSeeTheItemInTheCart(String itemName) {
    Assert.assertFalse(cartPage.isItemInCart(itemName), "User should not see the " + itemName + " item in the cart");
  }
}
