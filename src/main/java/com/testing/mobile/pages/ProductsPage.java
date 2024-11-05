package com.testing.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
  AndroidDriver driver;
  WebDriverWait wait;

  public ProductsPage(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By title = By.xpath("//android.widget.TextView[@text=\"Products\"]");

  public Boolean isDisplayed() {
    WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    return titleElement.isDisplayed();
  }

  public String getTitle() {
    WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    return titleElement.getText();
  }

  public void clickProduct(String productName) {
    String xpathExpression = String.format(
      "//android.widget.TextView[@content-desc='store item text' and @text='%s']/parent::android.view.ViewGroup",
      productName
    );
    WebElement productItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
    productItem.click();
  }

  public void scrollToProduct(String productName) {
    String uiAutomator = String.format(
      "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"%s\"))",
      productName
    );

    driver.findElement(AppiumBy.androidUIAutomator(uiAutomator));

    wait.until(ExpectedConditions.elementToBeClickable(
      AppiumBy.xpath("//android.widget.TextView[@content-desc='store item text' and @text='" + productName + "']/parent::android.view.ViewGroup")
    ));
  }
}
