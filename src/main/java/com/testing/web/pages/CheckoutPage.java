package com.testing.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class CheckoutPage {
  WebDriver driver;
  WebDriverWait wait;

  public CheckoutPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  By menuButton = By.id("react-burger-menu-btn");
  By cartButton = By.cssSelector("[data-test='shopping-cart-link']");
  By firstName = By.id("first-name");
  By lastName = By.id("last-name");
  By postalCode = By.id("postal-code");
  By continueButton = By.id("continue");
  By errorMessageContainer = By.xpath("//*[@id='checkout_info_container']/div/form/div[1]/div[4]");
  By errorMessage = By.xpath("//*[@id='checkout_info_container']/div/form/div[1]/div[4]/h3");
  By finishButton = By.id("finish");
  By backHomeButton = By.id("back-to-products");

  public Boolean isCheckoutPage() {
    return driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-one.html");
  }

  public void fillCheckoutInformation(Map<String, String> checkoutInformation) {
    WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(firstName));
    String firstNameValue = checkoutInformation.get("first_name");
    if (firstNameValue != null) firstNameInput.sendKeys(firstNameValue);

    WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(lastName));
    String lastNameValue = checkoutInformation.get("last_name");
    if (lastNameValue != null) lastNameInput.sendKeys(lastNameValue);

    WebElement postalCodeInput = wait.until(ExpectedConditions.elementToBeClickable(postalCode));
    String postalCodeValue = checkoutInformation.get("postal_code");
    if (postalCodeValue != null) postalCodeInput.sendKeys(postalCodeValue);
  }

  public void clickToContinueButton() {
    WebElement continueButtonElement = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
    continueButtonElement.click();
  }

  public Boolean isCheckoutOverviewPage() {
    return driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-two.html");
  }

  public Boolean isCheckoutErrorMessageShown() {
    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
    WebElement errorMessageContainerElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageContainer));
    return errorMessageContainerElement.findElements(errorMessage).size() > 0;
  }

  public void clickToFinishButton() {
    WebElement finishButtonElement = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
    finishButtonElement.click();
  }

  public Boolean isCheckoutCompletePage() {
    return driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-complete.html");
  }

  public void clickToBackHomeButton() {
    WebElement backHomeButtonElement = wait.until(ExpectedConditions.elementToBeClickable(backHomeButton));
    backHomeButtonElement.click();
  }
}
