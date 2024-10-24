package com.testing.web.pages;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginPageTest {
  private RemoteWebDriver driver;
  private LoginPage loginPage;
  String gridUrl = System.getProperty("selenium.grid.url", "http://localhost:4444/wd/hub");

  @Before
  public void setup() throws MalformedURLException {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--window-size=1920,1080");
    options.addArguments("--remote-allow-origins=*");

    driver = new RemoteWebDriver(
      new URL(gridUrl),
      options
    );

    loginPage = new LoginPage(driver);
  }

  @Given("User on the Sauce Demo login page {string}")
  public void navigateToLoginPage(String url) {
    driver.get(url);
  }

  @Given("the Chrome browser is configured")
  public void configureBrowser() {
    Assert.assertNotNull(driver, "Browser should be configured");
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

  @Then("User should see an error message {string}")
  public void verifyErrorMessage(String expectedErrorMessage) {
    Assert.assertEquals(
      loginPage.getErrorMessage(),
      expectedErrorMessage,
      "Login failed but error message not correct"
    );
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
