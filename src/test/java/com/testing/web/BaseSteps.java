package com.testing.web;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.testng.Assert;

import java.net.MalformedURLException;

public class BaseSteps {
  @Before
  public void configureBrowser() throws MalformedURLException {
    new TestContext();
    Assert.assertNotNull(TestContext.getDriver(), "Browser should be configured");
  }

  @After
  public void tearDown() {
    TestContext.quitDriver();
  }
}
