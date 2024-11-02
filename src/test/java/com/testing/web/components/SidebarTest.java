package com.testing.web.components;

import com.testing.web.TestContext;
import io.cucumber.java.en.Then;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class SidebarTest {
  private final Sidebar sidebar;
  private final RemoteWebDriver driver;

  public SidebarTest() {
    this.driver = TestContext.getDriver();
    this.sidebar = new Sidebar(driver);
  }

  @Then("User should see the sidebar")
  public void userShouldSeeTheSidebar() {
    Assert.assertTrue(sidebar.isSidebarDisplayed(), "Sidebar should be displayed");
  }
}
