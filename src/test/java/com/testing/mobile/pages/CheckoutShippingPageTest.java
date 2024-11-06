package com.testing.mobile.pages;

import com.testing.mobile.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class CheckoutShippingPageTest {
  private final AndroidDriver driver;
  private final WebDriverWait wait;
  private final CheckoutShippingPage checkoutShippingPage;

  public CheckoutShippingPageTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
    this.checkoutShippingPage = new CheckoutShippingPage(driver);
  }

  @Then("User should see checkout page")
  public void userShouldSeeCheckoutPage() {
    Assert.assertEquals(checkoutShippingPage.getTitle(), "Checkout");
  }

  @When("User fill shipping address with this information:")
  public void userFillShippingAddressWithThisInformation(DataTable dataTable) {
    Map<String, String> shippingInformation = dataTable.asMap(String.class, String.class);

    checkoutShippingPage.fillShippingInformation(shippingInformation);
  }

  @And("User click on the to payment button")
  public void userClickOnTheToPaymentButton() {
    checkoutShippingPage.clickToPaymentButton();
  }

  @Then("User should see error message for fields:")
  public void userShouldSeeErrorMessageForFields(DataTable dataTable) {
    List<String> errorFields = dataTable.asList(String.class);

    System.out.println("errorFields" + errorFields);
    for (String field : errorFields) {
      Assert.assertTrue(checkoutShippingPage.errorMessageIsDisplayed(field), "Error message for field " + field + " is not displayed");
    }
  }
}
