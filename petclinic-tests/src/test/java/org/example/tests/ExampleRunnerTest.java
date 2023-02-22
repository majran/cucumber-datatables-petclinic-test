package org.example.tests;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters({
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber-reports/cucumber.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example"),
        @ConfigurationParameter(key = ANSI_COLORS_DISABLED_PROPERTY_NAME, value = "true"),
        @ConfigurationParameter(key = "cucumber.junit-platform.naming-strategy", value = "long"),
        @ConfigurationParameter(key = "cucumber.execution.parallel.enabled", value = "true"),
        @ConfigurationParameter(key = "cucumber.execution.parallel.config.strategy", value = "custom"),
        @ConfigurationParameter(key = "cucumber.execution.parallel.config.custom.class", value = "org.example.tests.ParallelConfigurer"),
        @ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false"),
        @ConfigurationParameter(key = PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "false"),
        @ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
})
public class ExampleRunnerTest {
}