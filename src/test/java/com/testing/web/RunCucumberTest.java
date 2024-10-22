package com.testing.web;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/resources/features/web",
  glue = "com.testing.web",
  plugin = {"pretty", "html:target/cucumber-web-reports.html"}
)
public class RunCucumberTest {
}
