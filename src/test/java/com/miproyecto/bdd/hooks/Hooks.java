package com.miproyecto.bdd.hooks;

import com.miproyecto.bdd.context.TestContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before(value = "@ui or @login", order = 0)
    public void setUpWebDriver() {
        if (TestContext.getDriver() != null) {
            return;
        }
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        options.addArguments("--disable-cache");
        WebDriver driver = new ChromeDriver(options);
        TestContext.setDriver(driver);
    }

    @After(value = "@ui or @login", order = 0)
    public void tearDownWebDriver() {
        WebDriver driver = TestContext.getDriver();
        if (driver != null) {
            driver.quit();
            TestContext.clearDriver();
        }
    }

    @After(order = 1)
    public void clearApiContext() {
        TestContext.clearLastResponse();
        TestContext.clearCreatedUserId();
    }
}
