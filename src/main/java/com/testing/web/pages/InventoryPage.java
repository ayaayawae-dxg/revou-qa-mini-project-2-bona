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

  String addToCartButton = "[data-test='add-to-cart-sauce-labs-%s']";
  String removeItemButton = "[data-test='remove-sauce-labs-%s']";
  String itemImage = "[data-test='inventory-item-sauce-labs-%s-img']";

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

  public void addItemToCart(String itemName) {
    By addToCartButtonBy = By.cssSelector(String.format(addToCartButton, itemName));
    WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonBy));
    addToCartButton.click();
  }

  public Boolean isAddToCartItemShown(String itemName) {
    By addToCartButtonBy = By.cssSelector(String.format(addToCartButton, itemName));
    WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonBy));
    return addToCartButton.isDisplayed();
  }

  public Boolean isRemoveFromCartItemShown(String itemName) {
    By removeFromCartButtonBy = By.cssSelector(String.format(removeItemButton, itemName));
    WebElement removeFromCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(removeFromCartButtonBy));
    return removeFromCartButton.isDisplayed();
  }

  public void removeItemFromCart(String itemName) {
    By removeFromCartButtonBy = By.cssSelector(String.format(removeItemButton, itemName));
    WebElement removeFromCartButton = wait.until(ExpectedConditions.elementToBeClickable(removeFromCartButtonBy));
    removeFromCartButton.click();
  }

  public void clickTitleOfItem(String itemName) {
    WebElement itemLink = wait.until(ExpectedConditions.elementToBeClickable(
      By.xpath(String.format("//img[@data-test='inventory-item-sauce-labs-%s-img']/parent::a", itemName))
    ));

    itemLink.click();
  }
}
