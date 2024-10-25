package com.testing.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
  AndroidDriver driver;
  WebDriverWait wait;

  public LoginPage(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By username = By.xpath("//android.widget.EditText[@content-desc=\"Username input field\"]");
  By password = By.xpath("//android.widget.EditText[@content-desc=\"Password input field\"]");
  By loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"Login button\"]");
  By usernameErrorMessage = By.xpath("//android.view.ViewGroup[@content-desc=\"Username-error-message\"]");
  By passwordErrorMessage = By.xpath("//android.view.ViewGroup[@content-desc=\"Password-error-message\"]");
  By errorMessage = By.xpath("//android.view.ViewGroup[@content-desc=\"generic-error-message\"]//android.widget.TextView");

  public void setUsername(String strUsername) {
    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    usernameField.sendKeys(strUsername);
  }

  public void setPassword(String strPassword) {
    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
    passwordField.sendKeys(strPassword);
  }

  public void clickLogin() {
    WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    loginBtn.click();
  }

  public String getUsernameErrorMessage() {
    WebElement usernameError = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameErrorMessage));
    return usernameError.getText();
  }

  public String getPasswordErrorMessage() {
    WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMessage));
    return passwordError.getText();
  }

  public String getErrorMessage() {
    WebElement errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    return errorText.getText();
  }
}
