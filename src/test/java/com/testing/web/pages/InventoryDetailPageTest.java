package com.testing.web.pages;

import com.testing.web.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class InventoryDetailPageTest {
  private final RemoteWebDriver driver;
  private final InventoryDetailPage inventoryDetailPage;

  public InventoryDetailPageTest() {
    this.driver = TestContext.getDriver();
    this.inventoryDetailPage = new InventoryDetailPage(driver);
  }

  @Then("User will be redirected to the detail page of {string} item")
  public void userWillBeRedirectedToTheDetailPageOfItem(String itemName) {
    Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory-item.html"), "User should be redirected to the detail page");
    Assert.assertTrue(inventoryDetailPage.isImageNameDisplayed(itemName), "Image should be displayed");
  }

  @When("User clicks the add to cart button")
  public void userClicksTheAddToCartButton() {
    inventoryDetailPage.clickAddToCartButton();
  }

  @Then("the add to cart button should change to remove")
  public void theAddToCartButtonShouldChangeToRemove() {
    Assert.assertTrue(inventoryDetailPage.isRemoveFromCartButtonShown(), "Add to cart button should be changed to remove");
  }

  @When("User clicks the remove button")
  public void userClicksTheRemoveButton() {
    inventoryDetailPage.clickRemoveFromCartButton();
  }

  @Then("the remove button should change to add to cart")
  public void theRemoveButtonShouldChangeToAddToCart() {
    Assert.assertTrue(inventoryDetailPage.isAddToCartButtonShown(), "Remove button should be changed to add to cart");
  }

  @When("User clicks the back to products button")
  public void userClicksTheBackToProductsButton() {
    inventoryDetailPage.clickBackToProductsButton();
  }

  @Then("User will be redirected to the inventory page")
  public void userWillBeRedirectedToTheInventoryPage() {
    Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"), "User should be redirected to the inventory page");
  }
}
