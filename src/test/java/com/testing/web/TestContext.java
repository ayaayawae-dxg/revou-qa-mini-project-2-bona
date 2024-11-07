package com.testing.web;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestContext {
  private static RemoteWebDriver driver;
  private static String gridUrl = System.getProperty("selenium.grid.url", "http://localhost:4444/wd/hub");

  public TestContext() throws MalformedURLException {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--window-size=1920,1080");
    options.addArguments("--remote-allow-origins=*");

    driver = new RemoteWebDriver(new URL(gridUrl), options);
  }

  public static RemoteWebDriver getDriver() {
    return driver;
  }

  public static void quitDriver() {
    driver.quit();
  }
}
