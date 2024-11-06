package com.testing.web.components;

import com.testing.web.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class CartIconTest {
  private final Sidebar sidebar;
  private final CartIcon cartIcon;
  private final RemoteWebDriver driver;

  public CartIconTest() {
    this.driver = TestContext.getDriver();
    this.sidebar = new Sidebar(driver);
    this.cartIcon = new CartIcon(driver);
  }

  @Then("the cart badge should show {int} items")
  public void theCartBadgeShouldShowItems(int quantity) {
    Assert.assertEquals(cartIcon.getQuantity(), quantity);
  }

  @When("User click on the cart button")
  public void userClickOnTheCartButton() {
    cartIcon.clickCartButton();
  }

  @Then("the cart badge should be empty")
  public void theCartBadgeShouldBeEmpty() {
    Assert.assertTrue(cartIcon.isCartBadgeEmpty(), "Cart badge should be empty");
  }
}
