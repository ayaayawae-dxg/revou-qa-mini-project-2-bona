package com.testing.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class CheckoutPaymentPage {
  AndroidDriver driver;
  WebDriverWait wait;

  public CheckoutPaymentPage(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By title = AppiumBy.xpath("//android.widget.TextView[@text='Checkout']");
  By subTitle = AppiumBy.xpath("//android.widget.TextView[@text='Enter a payment method']");

  public String getTitle() {
    WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    return titleElement.getText();
  }

  public String getSubTitle() {
    WebElement subTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subTitle));
    return subTitleElement.getText();
  }
}
