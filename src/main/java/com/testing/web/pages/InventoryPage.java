package com.testing.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
  WebDriver driver;
  WebDriverWait wait;

  public InventoryPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By menuButton = By.id("react-burger-menu-btn");
  By cartButton = By.cssSelector("[data-test='shopping-cart-link']");
  By inventoryContainer = By.id("inventory_container");

  public void clickMenuButton() {
    WebElement menuButtonElement = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
    menuButtonElement.click();
  }

  public void clickCartButton() {
    WebElement cartButtonElement = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
    cartButtonElement.click();
  }

  public Boolean isDisplayed() {
    WebElement inventoryContainerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer));
    return inventoryContainerElement.isDisplayed();
  }

  public void addItemToCart(String id) {
    WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='add-to-cart-sauce-labs-" + id + "']")));
    addToCartButton.click();
  }

  public Boolean isAddToCartItemShown(String id) {
    WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='add-to-cart-sauce-labs-" + id + "']")));
    return item.isDisplayed();
  }

  public Boolean isRemoveFromCartItemShown(String id) {
    WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='remove-sauce-labs-" + id + "']")));
    return item.isDisplayed();
  }
}
