package com.testing.mobile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
  features = "src/test/resources/features/mobile",
  glue = "com.testing.mobile",
  plugin = {
    "pretty",
    "html:target/cucumber-reports/mobile/cucumber.html",
    "json:target/cucumber-reports/mobile/cucumber.json"
  }
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
  @Override
  @DataProvider(parallel = false)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}