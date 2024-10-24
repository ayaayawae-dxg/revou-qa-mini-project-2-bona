package com.testing.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage {
  AndroidDriver driver;

  public LoginPage(AndroidDriver driver) {
    this.driver = driver;
  }

  By username = By.xpath("//android.widget.EditText[@content-desc=\"Username input field\"]");
  By password = By.xpath("//android.widget.EditText[@content-desc=\"Password input field\"]");
  By loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"Login button\"]");
  By usernameErrorMessage = By.xpath("//android.view.ViewGroup[@content-desc=\"Username-error-message\"]");
  By passwordErrorMessage = By.xpath("//android.view.ViewGroup[@content-desc=\"Password-error-message\"]");
  By errorMessage = By.xpath("//android.view.ViewGroup[@content-desc=\"generic-error-message\"]");

  public void setUsername(String strUsername) {
    driver.findElement(username).sendKeys(strUsername);
  }

  public void setPassword(String strPassword) {
    driver.findElement(password).sendKeys(strPassword);
  }

  public void clickLogin() {
    driver.findElement(loginButton).click();
  }

  public String getUsernameErrorMessage() {
    return driver.findElement(usernameErrorMessage).getText();
  }

  public String getPasswordErrorMessage() {
    return driver.findElement(passwordErrorMessage).getText();
  }

  public String getErrorMessage() {
    return driver.findElement(errorMessage).getText();
  }
}
