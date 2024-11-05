package com.testing.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
  AndroidDriver driver;
  WebDriverWait wait;

  public CartPage(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By productName = AppiumBy.xpath("(//android.widget.TextView[@content-desc='product label'])[1]");
  By quantity = AppiumBy.xpath("((//android.view.ViewGroup[@content-desc='counter amount'])[1]/android.widget.TextView)");
  By removeButton = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='remove item'])[1]");
  String colorXPath = "(//android.view.ViewGroup[@content-desc='%s'])[1]";

  public String getProductName() {
    WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(this.productName));
    return productName.getText();
  }

  public Integer getQuantity() {
    WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(this.quantity));
    return Integer.parseInt(quantity.getText());
  }

  public String getColor(String color) {
    WebElement colorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(String.format(colorXPath, color))));
    return colorElement.getAttribute("content-desc");
  }

  public void clickRemoveButton() throws InterruptedException {
    WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(this.removeButton));
    removeButton.click();
  }
  
  public boolean isProductPresent(String productName, String color) {
    try {
      WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
      shortWait.until(ExpectedConditions.visibilityOfElementLocated(this.productName)).isDisplayed();
      shortWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(String.format(colorXPath, color)))).isDisplayed();
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }
}
