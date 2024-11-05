package com.testing.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsDetailPage {
  AndroidDriver driver;
  WebDriverWait wait;

  public ProductsDetailPage(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By quantityText = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView");
  By decreaseQuantityButton = AppiumBy.accessibilityId("counter minus button");
  By increaseQuantityButton = AppiumBy.accessibilityId("counter plus button");
  By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");

  public String getProductName(String productName) {
    String productNameXPath = "//android.widget.TextView[@text='" + productName + "']";
    WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productNameXPath)));
    return productNameElement.getText();
  }

  public void increaseQuantity(int quantity) {
    for (int i = 0; i < quantity; i++) {
      WebElement increaseQuantityButton = wait.until(ExpectedConditions.visibilityOfElementLocated(this.increaseQuantityButton));
      increaseQuantityButton.click();
    }
  }

  public void decreaseQuantity(int quantity) {
    for (int i = 0; i < quantity; i++) {
      WebElement decreaseQuantityButton = wait.until(ExpectedConditions.visibilityOfElementLocated(this.decreaseQuantityButton));
      decreaseQuantityButton.click();
    }
  }
  
  public void selectColor(String color) {
    WebElement colorButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(color)));
    colorButton.click();
  }

  public void clickAddToCartButton() {
    WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(this.addToCartButton));
    addToCartButton.click();
  }

  public boolean isAddToCartButtonEnabled() {
    WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(this.addToCartButton));
    return addToCartButton.isEnabled();
  }

  public boolean isColorSelectionOptionsDisplayed(String color) {
    WebElement colorSelectionOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(color)));
    return colorSelectionOptions.isDisplayed();
  }

  public int getQuantity() {
    WebElement quantityText = wait.until(ExpectedConditions.visibilityOfElementLocated(this.quantityText));
    return Integer.parseInt(quantityText.getText());
  }
}
