package com.testing.web.pages;

import com.testing.web.BaseSteps;
import com.testing.web.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

public class LoginPageTest {
  private final LoginPage loginPage;
  private final RemoteWebDriver driver;

  public LoginPageTest() throws MalformedURLException {
    new TestContext();
    this.driver = TestContext.getDriver();
    this.loginPage = new LoginPage(driver);
  }

  @Given("User on the Sauce Demo login page {string}")
  public void navigateToLoginPage(String url) {
    driver.get(url);
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
  public void clickLoginButton() {
    loginPage.clickLogin();
  }

  @Then("User should be redirected to the inventory page {string}")
  public void verifyRedirectToInventoryPage(String expectedUrl) {
    Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login success but not navigated to inventory page");
  }

  @Then("User should see a login error message {string}")
  public void verifyErrorMessage(String expectedErrorMessage) {
    Assert.assertEquals(
      loginPage.getErrorMessage(),
      expectedErrorMessage,
      "Login failed but error message not correct"
    );
  }
}
