package com.testing.mobile.pages;

import com.testing.mobile.TestContext;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductsPageTest {
  private final AndroidDriver driver;
  private final WebDriverWait wait;
  private final ProductsPage productsPage;
  private final ProductsDetailPage productsDetailPage;

  public ProductsPageTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
    this.productsPage = new ProductsPage(driver);
    this.productsDetailPage = new ProductsDetailPage(driver);
  }

  @Given("User on the products page")
  public void userOnTheProductsPage() {
    WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("open menu")));
    menuButton.click();

    WebElement productItem = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("menu item catalog")));
    productItem.click();
  }

  @When("User scroll to the product {string}")
  public void userScrollToTheProduct(String productName) {
    productsPage.scrollToProduct(productName);
  }

  @When("User click on the product {string}")
  public void userClickOnTheProduct(String productName) {
    productsPage.clickProduct(productName);
  }

  @Then("User should see the products list")
  public void userShouldSeeTheProductsList() {
    Assert.assertTrue(productsPage.isDisplayed(), "User should see the products list");
  }

  @Then("User should see the product details for {string}")
  public void userShouldSeeTheProductDetailsFor(String productName) {
    Assert.assertEquals(productsDetailPage.getProductName(productName), productName, "User should see the product details for " + productName);
  }
}
