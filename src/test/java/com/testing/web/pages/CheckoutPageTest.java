package com.testing.web.pages;

import com.testing.web.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.util.Map;

public class CheckoutPageTest {
  private final RemoteWebDriver driver;
  private final CheckoutPage checkoutPage;

  public CheckoutPageTest() {
    this.driver = TestContext.getDriver();
    this.checkoutPage = new CheckoutPage(driver);
  }

  @Then("User should see the checkout page")
  public void userShouldSeeTheCheckoutPage() {
    Assert.assertTrue(checkoutPage.isCheckoutPage(), "User should see the checkout page");
  }

  @When("User fills the form with this information:")
  public void userFillsTheFormWithThisInformation(DataTable dataTable) {
    Map<String, String> checkoutInformation = dataTable.asMap(String.class, String.class);

    checkoutPage.fillCheckoutInformation(checkoutInformation);
  }

  @And("User clicks the continue button")
  public void userClicksTheContinueButton() {
    checkoutPage.clickToContinueButton();
  }

  @Then("User should see the checkout overview page")
  public void userShouldSeeTheCheckoutOverviewPage() {
    Assert.assertTrue(checkoutPage.isCheckoutOverviewPage(), "User should see the checkout overview page");
  }

  @Then("User should see the checkout error message")
  public void userShouldSeeTheCheckoutErrorMessage() {
    Assert.assertTrue(checkoutPage.isCheckoutErrorMessageShown(), "User should see the checkout error message");
  }

  @And("User clicks the finish button")
  public void userClicksTheFinishButton() {
    checkoutPage.clickToFinishButton();
  }

  @Then("User should see the checkout complete page")
  public void userShouldSeeTheCheckoutCompletePage() {
    Assert.assertTrue(checkoutPage.isCheckoutCompletePage(), "User should see the checkout complete page");
  }

  @When("User clicks the Back Home button")
  public void userClicksTheBackHomeButton() {
    checkoutPage.clickToBackHomeButton();
  }
}
