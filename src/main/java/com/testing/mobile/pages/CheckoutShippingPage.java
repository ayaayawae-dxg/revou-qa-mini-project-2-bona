package com.testing.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CheckoutShippingPage {
  AndroidDriver driver;
  WebDriverWait wait;

  public CheckoutShippingPage(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  String fullnameIdentifier = "Full Name*";
  String addressLine1Identifier = "Address Line 1*";
  String addressLine2Identifier = "Address Line 2";
  String cityIdentifier = "City*";
  String stateRegionIdentifier = "State/Region";
  String zipCodeIdentifier = "Zip Code*";
  String countryIdentifier = "Country*";

  By title = AppiumBy.xpath("//android.widget.TextView[@text='Checkout']");
  By fullName = AppiumBy.accessibilityId(fullnameIdentifier + " input field");
  By addressLine1 = AppiumBy.accessibilityId(addressLine1Identifier + " input field");
  By addressLine2 = AppiumBy.accessibilityId(addressLine2Identifier + " input field");
  By city = AppiumBy.accessibilityId(cityIdentifier + " input field");
  By stateRegion = AppiumBy.accessibilityId(stateRegionIdentifier + " input field");
  By zipCode = AppiumBy.accessibilityId(zipCodeIdentifier + " input field");
  By country = AppiumBy.accessibilityId(countryIdentifier + " input field");
  By toPaymentButton = AppiumBy.accessibilityId("To Payment button");

  public String getTitle() {
    WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    return titleElement.getText();
  }

  public void fillShippingInformation(Map<String, String> shippingInformation) {
    WebElement fullNameInput = wait.until(ExpectedConditions.elementToBeClickable(fullName));
    String fullNameValue = shippingInformation.get("full_name");
    if (fullNameValue != null) fullNameInput.sendKeys(fullNameValue);

    WebElement addressLine1Input = wait.until(ExpectedConditions.elementToBeClickable(addressLine1));
    String addressLine1Value = shippingInformation.get("address_line_1");
    if (addressLine1Value != null) addressLine1Input.sendKeys(addressLine1Value);

    WebElement addressLine2Input = wait.until(ExpectedConditions.elementToBeClickable(addressLine2));
    String addressLine2Value = shippingInformation.get("address_line_2");
    if (addressLine2Value != null) addressLine2Input.sendKeys(addressLine2Value);

    WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(city));
    String cityValue = shippingInformation.get("city");
    if (cityValue != null) cityInput.sendKeys(cityValue);

    WebElement stateInput = wait.until(ExpectedConditions.elementToBeClickable(stateRegion));
    String stateValue = shippingInformation.get("state_region");
    if (stateValue != null) stateInput.sendKeys(stateValue);

    WebElement zipCodeInput = wait.until(ExpectedConditions.elementToBeClickable(zipCode));
    String zipCodeValue = shippingInformation.get("zip_code");
    if (zipCodeValue != null) zipCodeInput.sendKeys(zipCodeValue);

    WebElement countryInput = wait.until(ExpectedConditions.elementToBeClickable(country));
    String countryValue = shippingInformation.get("country");
    if (countryValue != null) countryInput.sendKeys(countryValue);
  }

  public void clickToPaymentButton() {
    WebElement toPaymentButtonElement = wait.until(ExpectedConditions.elementToBeClickable(toPaymentButton));
    toPaymentButtonElement.click();
  }

  public boolean errorMessageIsDisplayed(String errorField) {
    try {
      String errorIdentifier;

      switch (errorField) {
        case "full_name" -> errorIdentifier = fullnameIdentifier;
        case "address_line_1" -> errorIdentifier = addressLine1Identifier;
        case "address_line_2" -> errorIdentifier = addressLine2Identifier;
        case "city" -> errorIdentifier = cityIdentifier;
        case "state_region" -> errorIdentifier = stateRegionIdentifier;
        case "zip_code" -> errorIdentifier = zipCodeIdentifier;
        case "country" -> errorIdentifier = countryIdentifier;
        default -> {
          return false;
        }
      };

      String errorXpath = "//*[@content-desc='" + errorIdentifier + "-error-message']/android.widget.TextView";

      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(errorXpath)));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
