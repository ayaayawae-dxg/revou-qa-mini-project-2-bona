package com.testing.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
  WebDriver driver;
  WebDriverWait wait;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By username = By.id("user-name");
  By password = By.id("password");
  By loginButton = By.id("login-button");
  By errorMessageContainer = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]");
  By errorMessage = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");
  By errorButtonClose = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3/button");

  public void setUsername(String strUsername) {
    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    usernameField.sendKeys(strUsername);
  }

  public void setPassword(String strPassword) {
    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
    passwordField.sendKeys(strPassword);
  }

  public void clickLogin() {
    WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    loginButtonElement.click();
  }

  public String getErrorMessage() {
    WebElement errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    return errorText.getText();
  }

  public Boolean errorMessageIsNotShown() {
    WebElement errorMessageContainerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageContainer));
    return errorMessageContainerElement.findElements(errorMessage).isEmpty();
  }

  public void clickCloseErrorMessage() {
    WebElement errorButtonCloseElement = wait.until(ExpectedConditions.elementToBeClickable(errorButtonClose));
    errorButtonCloseElement.click();
  }

  public void login(String username, String password) {
    setUsername(username);
    setPassword(password);
    clickLogin();
  }
}
