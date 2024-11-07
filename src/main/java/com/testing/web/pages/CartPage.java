package com.testing.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
  WebDriver driver;
  WebDriverWait wait;

  public CartPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By menuButton = By.id("react-burger-menu-btn");
  By cartButton = By.cssSelector("[data-test='shopping-cart-link']");
  By checkoutButton = By.id("checkout");
  String removeButtonItem = "remove-sauce-labs-%s";

  public Boolean isItemInCart(String itemName) {
    try {
      WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));

      By removeButton = By.id(String.format(removeButtonItem, itemName));
      WebElement removeButtonElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
      return removeButtonElement.isDisplayed();
    } catch (TimeoutException e) {
      return false;
    }
  }

  public void clickCheckoutButton() {
    WebElement checkoutButtonElement = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
    checkoutButtonElement.click();
  }
}
