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
}
