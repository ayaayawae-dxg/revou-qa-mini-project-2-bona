package com.testing.mobile.components;

import com.testing.mobile.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartIconTest {
  private final AndroidDriver driver;
  private final WebDriverWait wait;
  private final CartIcon cartIcon;

  public CartIconTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
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
}
