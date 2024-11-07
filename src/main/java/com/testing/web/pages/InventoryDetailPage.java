package com.testing.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryDetailPage {
  WebDriver driver;
  WebDriverWait wait;

  public InventoryDetailPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By menuButton = By.id("react-burger-menu-btn");
  By cartButton = By.cssSelector("[data-test='shopping-cart-link']");
  By addToCartButton = By.id("add-to-cart");
  By removeFromCartButton = By.id("remove");
  By backToProductsButton = By.id("back-to-products");
  String itemImage = "[data-test='item-sauce-labs-%s-img']";

  public Boolean isImageNameDisplayed(String itemName) {
    String itemImageSelector = String.format(itemImage, itemName);
    WebElement itemImageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(itemImageSelector)));
    return itemImageElement.isDisplayed();
  }

  public Boolean isRemoveFromCartButtonShown() {
    WebElement removeFromCartButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(removeFromCartButton));
    return removeFromCartButtonElement.isDisplayed();
  }

  public void clickAddToCartButton() {
    WebElement addToCartButtonElement = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
    addToCartButtonElement.click();
  }

  public void clickRemoveFromCartButton() {
    WebElement removeFromCartButtonElement = wait.until(ExpectedConditions.elementToBeClickable(removeFromCartButton));
    removeFromCartButtonElement.click();
  }

  public Boolean isAddToCartButtonShown() {
    WebElement addToCartButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
    return addToCartButtonElement.isDisplayed();
  }

  public void clickBackToProductsButton() {
    WebElement backToProductsButtonElement = wait.until(ExpectedConditions.elementToBeClickable(backToProductsButton));
    backToProductsButtonElement.click();
  }
}
