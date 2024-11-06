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

  @When("User fill payment fields with this information:")
  public void userFillPaymentFieldsWithThisInformation(DataTable dataTable) {
    Map<String, String> paymentInformation = dataTable.asMap(String.class, String.class);

    checkoutPaymentPage.fillPaymentInformation(paymentInformation);
  }

  @And("User click on the review order button {int} times")
  public void userClickOnTheReviewOrderButton(int times) throws InterruptedException {
    for (int i = 0; i < times; i++) {
      checkoutPaymentPage.clickReviewOrderButton();
      Thread.sleep(100);
    }
  }
}
