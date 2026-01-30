package com.miproyecto.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Runner de Cucumber con JUnit Platform Suite.
 * Ejecuta todos los escenarios BDD definidos en los .feature.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/miproyecto")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.miproyecto.bdd")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/reports/cucumber-report.html, json:target/reports/cucumber.json")
public class CucumberRunnerTest {
}
