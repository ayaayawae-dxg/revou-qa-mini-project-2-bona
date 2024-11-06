package com.testing.web;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
  features = "src/test/resources/features/web",
  glue = "com.testing.web",
  plugin = {
    "pretty",
    "html:target/cucumber-reports/web/cucumber.html",
    "json:target/cucumber-reports/web/cucumber.json"
  },
  tags = "@web"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}