package com.testing.mobile.pages;

import com.testing.mobile.TestContext;
import com.testing.mobile.components.CartIcon;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductsDetailPageTest {
  private final AndroidDriver driver;
  private final WebDriverWait wait;
  private final ProductsDetailPage productsDetailPage;
  private final CartIcon cartIcon;
  private final ProductsPage productsPage;

  public ProductsDetailPageTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
    this.productsDetailPage = new ProductsDetailPage(driver);
    this.cartIcon = new CartIcon(driver);
    this.productsPage = new ProductsPage(driver);
  }
  
  @Given("User add items {string} to the cart with quantity {int} and color {string}")
  public void userAddItemsToTheCartWithQuantityQuantityAndColor(String productName, Integer quantity, String color) {
    productsPage.scrollToProduct(productName);
    productsPage.clickProduct(productName);
    productsDetailPage.increaseQuantity(quantity - 1);
    productsDetailPage.selectColor(color);
    productsDetailPage.clickAddToCartButton();
  }

  @When("User increase the quantity by {int}")
  public void userIncreaseTheQuantityBy(int quantity) {
    productsDetailPage.increaseQuantity(quantity);
  }

  @When("User decrease the quantity by {int}")
  public void userDecreaseTheQuantityBy(int quantity) {
    productsDetailPage.decreaseQuantity(quantity);
  }

  @When("User select {string} color")
  public void userSelectColor(String color) {
    productsDetailPage.selectColor(color);
  }

  @When("User click Add to Cart button")
  public void userClickAddToCartButton() {
    productsDetailPage.clickAddToCartButton();
  }

  @Then("User should see {string} color selection")
  public void userShouldSeeColorSelection(String color) {
    Assert.assertTrue(productsDetailPage.isColorSelectionOptionsDisplayed(color));
  }

  @Then("the quantity should be {int}")
  public void theQuantityShouldBe(int quantity) {
    Assert.assertEquals(productsDetailPage.getQuantity(), quantity);
  }

  @Then("the add to cart button should be disabled")
  public void theAddToCartButtonShouldBeDisabled() {
    Assert.assertFalse(productsDetailPage.isAddToCartButtonEnabled());
  }

  @Then("the add to cart button should be enabled")
  public void theAddToCartButtonShouldBeEnabled() {
    Assert.assertTrue(productsDetailPage.isAddToCartButtonEnabled());
  }
}
