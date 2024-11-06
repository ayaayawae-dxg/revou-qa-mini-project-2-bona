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
    fullNameInput.sendKeys(shippingInformation.get("full_name"));

    WebElement addressLine1Input = wait.until(ExpectedConditions.elementToBeClickable(addressLine1));
    addressLine1Input.sendKeys(shippingInformation.get("address_line_1"));

    WebElement addressLine2Input = wait.until(ExpectedConditions.elementToBeClickable(addressLine2));
    addressLine2Input.sendKeys(shippingInformation.get("address_line_2"));

    WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(city));
    cityInput.sendKeys(shippingInformation.get("city"));

    WebElement stateInput = wait.until(ExpectedConditions.elementToBeClickable(stateRegion));
    stateInput.sendKeys(shippingInformation.get("state_region"));

    WebElement zipCodeInput = wait.until(ExpectedConditions.elementToBeClickable(zipCode));
    zipCodeInput.sendKeys(shippingInformation.get("zip_code"));

    WebElement countryInput = wait.until(ExpectedConditions.elementToBeClickable(country));
    countryInput.sendKeys(shippingInformation.get("country"));
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
