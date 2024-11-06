package com.testing.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
  By summaryTotalQuantity = AppiumBy.accessibilityId("total number");
  By summaryTotalPrice = AppiumBy.accessibilityId("total price");
  By checkoutButton = AppiumBy.accessibilityId("Proceed To Checkout button");
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

  public Integer getSummaryTotalQuantity() {
    WebElement totalQuantity = wait.until(ExpectedConditions.visibilityOfElementLocated(this.summaryTotalQuantity));
    return Integer.parseInt(totalQuantity.getText().split(" ")[0]);
  }

  public Float getSummaryTotalPrice() {
    WebElement totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(this.summaryTotalPrice));
    return Float.parseFloat(totalPrice.getText().replace("$", ""));
  }

  public Integer getItemsTotalQuantity() {
    By allQuantities = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView");
    List<WebElement> quantities = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allQuantities));
    
    return quantities.stream()
        .mapToInt(element -> Integer.parseInt(element.getText()))
        .sum();
  }

  public Float getItemsTotalPrice() {
    By allPrices = AppiumBy.xpath("//android.widget.TextView[@content-desc='product price']");
    By allQuantities = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView");
    
    List<WebElement> prices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allPrices));
    List<WebElement> quantities = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allQuantities));
    
    float total = 0;
    for (int i = 0; i < prices.size(); i++) {
        float price = Float.parseFloat(prices.get(i).getText().replace("$", ""));
        int quantity = Integer.parseInt(quantities.get(i).getText());
        total += price * quantity;
    }
    
    return total;
  }

  public void clickCheckoutButton() {
    WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(this.checkoutButton));
    checkoutButton.click();
  }
}
