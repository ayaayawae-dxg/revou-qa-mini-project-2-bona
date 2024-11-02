package com.testing.web.pages;

import com.testing.web.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class LoginPageTest {
  private final LoginPage loginPage;
  private final RemoteWebDriver driver;

  public LoginPageTest() {
    this.driver = TestContext.getDriver();
    this.loginPage = new LoginPage(driver);
  }

  @And("User on the Sauce Demo login page {string}")
  public void navigateToLoginPage(String url) {
    driver.get(url);
  }

  @Given("User see a login error message")
  public void userSeeALoginErrorMessage() {
    loginPage.setUsername("wrongUsername");
    loginPage.setPassword("wrongPassword");
    loginPage.clickLogin();
    Assert.assertTrue(loginPage.getErrorMessage().length() > 0, "Login error message should be shown");
  }

  @Given("User is logged in")
  public void userIsLoggedIn() {
    driver.get("https://www.saucedemo.com/");
      
    loginPage.setUsername("standard_user");
    loginPage.setPassword("secret_sauce");
    loginPage.clickLogin();
  }

  @When("User enter username {string}")
  public void userEnterUsernameUsername(String username) {
    loginPage.setUsername(username);
  }

  @And("User enter password {string}")
  public void userEnterPasswordPassword(String password) {
    loginPage.setPassword(password);
  }

  @When("User click the login button")
  public void userClickTheLoginButton() {
    loginPage.clickLogin();
  }

  @When("User click the close error message button")
  public void userClickTheCloseErrorMessageButton() {
    loginPage.clickCloseErrorMessage();
  }

  @Then("User should be redirected to the inventory page {string}")
  public void userShouldBeRedirectedToTheInventoryPage(String expectedUrl) {
    Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login success but not navigated to inventory page");
  }

  @Then("User should see a login error message {string}")
  public void userShouldSeeALoginErrorMessage(String expectedErrorMessage) {
    Assert.assertEquals(
      loginPage.getErrorMessage(),
      expectedErrorMessage,
      "Login failed but error message not correct"
    );
  }
  
  @Then("User should not see a login error message")
  public void userShouldNotSeeALoginErrorMessage() {
    Assert.assertTrue(loginPage.errorMessageIsNotShown(), "Login error message should not be shown");
  }
}
