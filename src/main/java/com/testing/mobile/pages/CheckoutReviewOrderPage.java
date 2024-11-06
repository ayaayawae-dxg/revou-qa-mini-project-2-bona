package com.testing.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class CheckoutReviewOrderPage {
  AndroidDriver driver;
  WebDriverWait wait;

  public CheckoutReviewOrderPage(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By title = AppiumBy.xpath("//android.widget.TextView[@text='Checkout']");
  By subTitle = AppiumBy.xpath("//android.widget.TextView[@text='Review your order']");
  By placeOrderButton = AppiumBy.accessibilityId("Place Order");

  public String getTitle() {
    WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    return titleElement.getText();
  }

  public String getSubTitle() {
    WebElement subTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subTitle));
    return subTitleElement.getText();
  }

  public void clickPlaceOrderButton() {
    WebElement placeOrderButtonElement = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
    placeOrderButtonElement.click();
  }
}
