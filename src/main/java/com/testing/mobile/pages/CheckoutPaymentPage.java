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

  String fullNameIdentifier = "Full Name*";
  String cardNumberIdentifier = "Card Number*";
  String expiryDateIdentifier = "Expiration Date*";
  String securityCodeIdentifier = "Security Code*";

  By title = AppiumBy.xpath("//android.widget.TextView[@text='Checkout']");
  By subTitle = AppiumBy.xpath("//android.widget.TextView[@text='Enter a payment method']");  
  By fullNameField = AppiumBy.accessibilityId(fullNameIdentifier + " input field");
  By cardNumberField = AppiumBy.accessibilityId(cardNumberIdentifier + " input field");
  By expiryDateField = AppiumBy.accessibilityId(expiryDateIdentifier + " input field");
  By securityCodeField = AppiumBy.accessibilityId(securityCodeIdentifier + " input field");
  By sameAddressCheckbox = AppiumBy.accessibilityId("checkbox for My billing address is the same as my shipping address.");
  By reviewOrderButton = AppiumBy.accessibilityId("Review Order button");

  public String getTitle() {
    WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    return titleElement.getText();
  }

  public String getSubTitle() {
    WebElement subTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subTitle));
    return subTitleElement.getText();
  }

  public void fillPaymentInformation(Map<String, String> paymentInformation) {
    WebElement fullNameInput = wait.until(ExpectedConditions.elementToBeClickable(fullNameField));
    String fullNameValue = paymentInformation.get("full_name");
    if (fullNameValue != null) fullNameInput.sendKeys(fullNameValue);

    WebElement cardNumberInput = wait.until(ExpectedConditions.elementToBeClickable(cardNumberField));
    String cardNumberValue = paymentInformation.get("card_number");
    if (cardNumberValue != null) cardNumberInput.sendKeys(cardNumberValue);

    WebElement expiryDateInput = wait.until(ExpectedConditions.elementToBeClickable(expiryDateField));
    String expiryDateValue = paymentInformation.get("expiry_date");
    if (expiryDateValue != null) expiryDateInput.sendKeys(expiryDateValue); 

    WebElement securityCodeInput = wait.until(ExpectedConditions.elementToBeClickable(securityCodeField));
    String securityCodeValue = paymentInformation.get("security_code");
    if (securityCodeValue != null) securityCodeInput.sendKeys(securityCodeValue); 
  }

  public void clickReviewOrderButton() {
    WebElement reviewOrderButtonElement = wait.until(ExpectedConditions.elementToBeClickable(reviewOrderButton));
    reviewOrderButtonElement.click();
  }
}
