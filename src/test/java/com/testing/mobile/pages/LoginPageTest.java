package com.testing.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginPageTest {
  private static AndroidDriver driver;
  private static LoginPage loginPage;
  private static WebDriverWait wait;
  static String gridUrl = System.getProperty("appium.url", "http://localhost:4723");

  @Before
  public static void setup() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("appium:deviceName", "emulator-5554");
    capabilities.setCapability("appium:platformVersion", "15");
    capabilities.setCapability("platformName", "android");
    capabilities.setCapability("appium:automationName", "UiAutomator2");
    capabilities.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.rn");
    capabilities.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.rn.MainActivity");

    driver = new AndroidDriver(new URL(gridUrl), capabilities);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    loginPage = new LoginPage(driver);
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

  @After
  public static void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
