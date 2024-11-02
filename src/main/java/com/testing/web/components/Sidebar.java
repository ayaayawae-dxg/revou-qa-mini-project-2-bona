package com.testing.web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Sidebar {
  WebDriver driver;
  WebDriverWait wait;

  public Sidebar(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By sidebarContainer = By.xpath("//*[@id=\"menu_button_container\"]/div/div[2]");
  By closeButton = By.id("react-burger-cross-btn");

  public Boolean isSidebarDisplayed() {
    WebElement sidebarContainerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarContainer));
    String hiddenAttribute = sidebarContainerElement.getAttribute("hidden");
    return hiddenAttribute == null || !hiddenAttribute.equals("true");
  }

  public void closeSidebar() {
    WebElement closeButtonElement = wait.until(ExpectedConditions.elementToBeClickable(closeButton));
    closeButtonElement.click();
  }
}
