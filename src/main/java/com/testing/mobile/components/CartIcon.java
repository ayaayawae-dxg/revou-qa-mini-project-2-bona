package com.testing.mobile.components;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartIcon {
  AndroidDriver driver;
  WebDriverWait wait;

  public CartIcon(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By iconContainer = AppiumBy.accessibilityId("cart badge");
  String iconContainerXPath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]";

  public int getQuantity() {
    try {
      List<WebElement> quantityText = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
          AppiumBy.xpath(iconContainerXPath + "/android.widget.TextView")));
      
      if (quantityText.isEmpty()) {
        return 0;
      }

      WebElement quantityElement = wait.until(ExpectedConditions.visibilityOf(quantityText.get(0)));
      String quantityStr = quantityElement.getText().trim();
      
      if (quantityStr.isEmpty()) {
        return 0;
      }
      
      return Integer.parseInt(quantityStr);
    } catch (Exception e) {
      return 0;
    }
  }

  public void clickCartButton() {
    WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(iconContainer));
    cartButton.click();
  }
}
