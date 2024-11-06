package com.testing.web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartIcon {
  WebDriver driver;
  WebDriverWait wait;

  public CartIcon(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  String iconContainerXPath = "//*[@id=\"shopping_cart_container\"]";
  By cartButton = By.xpath(iconContainerXPath + "/a");

  public int getQuantity() {
    try {
      List<WebElement> quantityText = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
        By.xpath(iconContainerXPath + "/a/span")));

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
    WebElement cartButtonElement = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
    cartButtonElement.click();
  }

  public boolean isCartBadgeEmpty() {
    return getQuantity() == 0;
  }
}
