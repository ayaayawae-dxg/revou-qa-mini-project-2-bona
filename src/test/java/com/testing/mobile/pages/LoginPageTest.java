package com.testing.mobile.pages;

import com.testing.mobile.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPageTest {
  private final LoginPage loginPage;
  private final AndroidDriver driver;
  private final WebDriverWait wait;

  public LoginPageTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
    this.loginPage = new LoginPage(driver);
  }

  @Given("User on the login page")
  public void userOnTheLoginPage() throws InterruptedException {
    WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
      By.xpath("//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView")));
    menuButton.click();

    WebElement loginMenuItem = wait.until(ExpectedConditions.elementToBeClickable(
      By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")));
    loginMenuItem.click();
  }

  @When("User enter username {string}")
  public void enterUsername(String username) {
    loginPage.setUsername(username);
  }

  @When("User enter password {string}")
  public void enterPassword(String password) {
    loginPage.setPassword(password);
  }

  @When("User click the login button")
  public void clickLoginButton() throws InterruptedException {
    loginPage.clickLogin();
    Thread.sleep(100);
  }

  @Then("User should be redirected to the product page")
  public void verifyRedirectToInventoryPage() {
    WebElement productPage = wait.until(ExpectedConditions.visibilityOfElementLocated(
      By.xpath("//android.widget.TextView[@text=\"Products\"]")));
    Assert.assertEquals(productPage.getText(), "Products", "Login successful but not navigated to inventory page");
  }

  @Then("User should see an error message {string}")
  public void seeAnErrorMessage(String errorMessage) {
    Assert.assertEquals(loginPage.getErrorMessage(), errorMessage, "Login failed, credentials not valid");
  }

  @Then("User should see username error message {string}")
  public void userShouldSeeUsernameErrorMessage(String errorMessage) {
    String actualError = loginPage.getUsernameErrorMessage();
    Assert.assertFalse(actualError.isEmpty(), "No error message is displayed when it should show: " + errorMessage);
    Assert.assertEquals(actualError, errorMessage, "Error message doesn't match expected text");
  }

  @And("User should see password error message {string}")
  public void userShouldSeePasswordErrorMessage(String errorMessage) {
    String actualError = loginPage.getPasswordErrorMessage();
    Assert.assertFalse(actualError.isEmpty(), "No error message is displayed when it should show: " + errorMessage);
    Assert.assertEquals(actualError, errorMessage, "Error message doesn't match expected text");
  }
}
