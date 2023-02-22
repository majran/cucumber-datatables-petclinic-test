package org.example.tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import lombok.extern.slf4j.Slf4j;
import org.example.po.Helpers;
import org.springframework.test.context.ContextConfiguration;

import java.util.logging.Level;

@Slf4j
@CucumberContextConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public final class Hooks {

    @Before
    public static void openApp() {
        Helpers.openPage("http://localhost:8080");
        PropertiesReader.loadConfig();
    }

    @Before
    public void enableAllureLogging() {
        log.info("Enabling allure logging");
        new AllureSelenide().enableLogs(LogType.BROWSER, Level.ALL);
    }

    @After
    public void cleanupFrontend() {
        Helpers.closeWebDriver();
    }
}