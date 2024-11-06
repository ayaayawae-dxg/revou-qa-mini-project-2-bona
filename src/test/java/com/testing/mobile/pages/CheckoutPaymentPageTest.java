package com.testing.mobile.pages;

import com.testing.mobile.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutPaymentPageTest {
  private final AndroidDriver driver;
  private final WebDriverWait wait;
  private final CheckoutPaymentPage checkoutPaymentPage;

  public CheckoutPaymentPageTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
    this.checkoutPaymentPage = new CheckoutPaymentPage(driver);
  }

  @Then("User should see checkout payment page")
  public void userShouldSeeCheckoutPaymentPage() {
    Assert.assertEquals(checkoutPaymentPage.getTitle(), "Checkout");
    Assert.assertEquals(checkoutPaymentPage.getSubTitle(), "Enter a payment method");
  }
}
