package com.testing.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
  features = "src/test/resources/features/api",
  glue = "com.testing.api",
  plugin = {
    "pretty",
    "html:target/cucumber-reports/api/cucumber.html",
    "json:target/cucumber-reports/api/cucumber.json"
  },
  tags = "@api"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}