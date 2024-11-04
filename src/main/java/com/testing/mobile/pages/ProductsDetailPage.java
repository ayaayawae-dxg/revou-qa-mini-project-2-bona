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

  public String getProductName(String productName) {
    String productNameXPath = "//android.widget.TextView[@text='" + productName + "']";
    WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productNameXPath)));
    return productNameElement.getText();
  }
}
