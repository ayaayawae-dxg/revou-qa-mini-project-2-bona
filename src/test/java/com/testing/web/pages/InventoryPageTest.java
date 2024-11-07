package com.testing.web.pages;

import com.testing.web.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

public class InventoryPageTest {
  private final InventoryPage inventoryPage;
  private final RemoteWebDriver driver;

  public InventoryPageTest() {
    this.driver = TestContext.getDriver();
    this.inventoryPage = new InventoryPage(driver);
  }

  @Given("User on the Sauce Demo inventory page {string}")
  public void userOnTheSauceDemoInventoryPage(String url) {
    driver.get(url);
    Assert.assertTrue(inventoryPage.isDisplayed(), "User should be logged in");
  }

  @Given("list of items exist in the inventory page")
  public void listOfItemsExistInTheInventoryPage() {
    Assert.assertTrue(inventoryPage.isAddToCartItemShown("backpack"), "Item should exist in the inventory page");
  }

  @When("User click the menu button")
  public void userClickTheMenuButton() {
    inventoryPage.clickMenuButton();
  }

  @When("User clicks the add to cart button for {string}")
  public void userClicksTheAddToCartButtonFor(String itemName) {
    inventoryPage.addItemToCart(itemName);
  }

  @Then("the add to cart button for {string} should change to remove")
  public void theAddToCartButtonForShouldChangeToRemove(String itemName) {
    Assert.assertTrue(inventoryPage.isRemoveFromCartItemShown(itemName), "Add to cart button should be changed to remove");
  }

  @When("User clicks the remove button for {string}")
  public void userClicksTheRemoveButtonFor(String itemName) {
    inventoryPage.removeItemFromCart(itemName);
  }

  @Then("the remove button for {string} should change to add to cart")
  public void theRemoveButtonForShouldChangeToAddToCart(String itemName) {
    Assert.assertTrue(inventoryPage.isAddToCartItemShown(itemName), "Remove button should be changed to add to cart");
  }

  @When("User clicks the title of {string} item")
  public void userClicksTheTitleOfItem(String itemName) {
    inventoryPage.clickTitleOfItem(itemName);
  }

  @Then("User should be redirected to the inventory page")
  public void userShouldBeRedirectedToTheInventoryPage() {
    Assert.assertTrue(inventoryPage.isInventoryPage(), "User should be redirected to the inventory page");
  }
}
