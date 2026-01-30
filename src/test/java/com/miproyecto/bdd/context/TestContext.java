package com.miproyecto.bdd.context;

import org.openqa.selenium.WebDriver;

import io.restassured.response.Response;

/**
 * Contexto compartido entre steps de Cucumber (WebDriver y respuesta API).
 */
public final class TestContext {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<Response> LAST_RESPONSE = new ThreadLocal<>();
    private static final ThreadLocal<String> CREATED_USER_ID = new ThreadLocal<>();

    private TestContext() {
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static void clearDriver() {
        DRIVER.remove();
    }

    public static Response getLastResponse() {
        return LAST_RESPONSE.get();
    }

    public static void setLastResponse(Response response) {
        LAST_RESPONSE.set(response);
    }

    public static void clearLastResponse() {
        LAST_RESPONSE.remove();
    }

    public static String getCreatedUserId() {
        return CREATED_USER_ID.get();
    }

    public static void setCreatedUserId(String id) {
        CREATED_USER_ID.set(id);
    }

    public static void clearCreatedUserId() {
        CREATED_USER_ID.remove();
    }

    public static void clearAll() {
        clearDriver();
        clearLastResponse();
        clearCreatedUserId();
    }
}
