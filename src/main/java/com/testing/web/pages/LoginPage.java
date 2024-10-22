package com.testing.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
  WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  By username = By.id("user-name");
  By password = By.id("password");
  By loginButton = By.id("login-button");
  By errorMessage = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");

  public void setUsername(String strUsername) {
    driver.findElement(username).sendKeys(strUsername);
  }

  public void setPassword(String strPassword) {
    driver.findElement(password).sendKeys(strPassword);
  }

  public void clickLogin() {
    driver.findElement(loginButton).click();
  }

  public String getErrorMessage() {
    return driver.findElement(errorMessage).getText();
  }
}
