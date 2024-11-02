package com.testing.web;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import org.testng.Assert;

public class BaseSteps {
  @Given("the Chrome browser is configured")
  public void configureBrowser() {
    Assert.assertNotNull(TestContext.getDriver(), "Browser should be configured");
  }

  @After
  public void tearDown() {
    TestContext.quitDriver();
  }
}
