package com.testing.web.pages;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginPageTest {
  private WebDriver driver;
  private LoginPage loginPage; 

  @Before
  public void setup() {
    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
    driver = new ChromeDriver();
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
