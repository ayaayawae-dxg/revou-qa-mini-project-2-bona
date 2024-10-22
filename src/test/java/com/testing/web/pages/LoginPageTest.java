package com.testing.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;

public class LoginPageTest {
  static WebDriver driver;
  static LoginPage loginPage;

  @BeforeClass
  public static void setup() {
    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");

    driver = new ChromeDriver();
    loginPage = new LoginPage(driver);
  }

  @Test
  public void loginWithValidCredentials() {
    String expectedHomeUrl = "https://www.saucedemo.com/inventory.html";
    driver.get("https://www.saucedemo.com");

    loginPage.setUsername("standard_user");
    loginPage.setPassword("secret_sauce");
    loginPage.clickLogin();

    Assert.assertEquals(driver.getCurrentUrl(), expectedHomeUrl, "Login success but not navigated to inventory page");
  }

  @Test
  public void loginWithInvalidCredentials() {
    String expectedErrorMessage = "Username and password do not match any user in this service";
    driver.get("https://www.saucedemo.com");

    loginPage.setUsername("user1");
    loginPage.setPassword("password");
    loginPage.clickLogin();

    Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Login failed but error message not correct");
  }

  @AfterClass
  public static void tearDown() {
    driver.quit();
  }
}
