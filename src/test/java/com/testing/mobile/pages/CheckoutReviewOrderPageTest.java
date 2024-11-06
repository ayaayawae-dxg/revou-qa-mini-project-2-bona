package com.testing.mobile.pages;

import com.testing.mobile.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Map;

public class CheckoutReviewOrderPageTest {
  private final AndroidDriver driver;
  private final WebDriverWait wait;
  private final CheckoutReviewOrderPage checkoutReviewOrderPage;

  public CheckoutReviewOrderPageTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
    this.checkoutReviewOrderPage = new CheckoutReviewOrderPage(driver);
  }

  @Then("User should see checkout review order page")
  public void userShouldSeeCheckoutReviewOrderPage() {
    try {
      Assert.assertEquals(checkoutReviewOrderPage.getTitle(), "Checkout");
      Assert.assertEquals(checkoutReviewOrderPage.getSubTitle(), "Review your order");
    } catch (Exception e) {
      Assert.fail("User should see checkout review order page");
    }
  }

  @And("User click on the place order button")
  public void userClickOnThePlaceOrderButton() {
    checkoutReviewOrderPage.clickPlaceOrderButton();
  }
}
