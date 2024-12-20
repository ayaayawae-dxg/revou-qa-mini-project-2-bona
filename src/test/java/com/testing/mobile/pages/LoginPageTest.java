package com.testing.mobile.pages;

import com.testing.mobile.TestContext;
import io.appium.java_client.AppiumBy;
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
  private final ProductsPage productsPage;

  public LoginPageTest() {
    this.driver = TestContext.getDriver();
    this.wait = TestContext.getWait();
    this.loginPage = new LoginPage(driver);
    this.productsPage = new ProductsPage(driver);
  }

  @Given("User on the login page")
  public void userOnTheLoginPage() throws InterruptedException {
    WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("open menu")));
    menuButton.click();

    WebElement loginMenuItem = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("menu item log in")));
    loginMenuItem.click();
  }

  @Given("User login with username {string} and password {string}")
  public void userLoginWithUsernameAndPassword(String username, String password) throws InterruptedException {
    userOnTheLoginPage();
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();
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
    Assert.assertEquals(productsPage.getTitle(), "Products", "Login successful but not navigated to inventory page");
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

  @Then("User should see the login page")
  public void userShouldSeeTheLoginPage() {
    Assert.assertEquals(loginPage.getLoginTitle(), "Login", "Login page not shown");
  }
}
