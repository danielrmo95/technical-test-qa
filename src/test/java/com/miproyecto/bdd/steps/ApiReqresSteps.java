package com.miproyecto.bdd.steps;

import com.miproyecto.bdd.context.TestContext;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ApiReqresSteps {

    private static final String BASE_URI = "https://reqres.in/api";
    private static final String API_KEY = "reqres_f223f2d1fa1f4ea69720b7956c5b8441";

    static {
        RestAssured.baseURI = BASE_URI;
    }

    @Cuando("creo un usuario con nombre {string} y trabajo {string}")
    public void creoUnUsuarioConNombreYTrabajo(String nombre, String trabajo) {
        String requestBody = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", nombre, trabajo);
        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-API-Key", API_KEY)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();
        TestContext.setLastResponse(response);
    }

    @Dado("que he creado un usuario")
    public void queHeCreadoUnUsuario() {
        String requestBody = "{\"name\": \"Test User\", \"job\": \"Automation Engineer\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-API-Key", API_KEY)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();
        TestContext.setLastResponse(response);
        Object idObj = response.jsonPath().get("id");
        String createdId = idObj != null ? idObj.toString() : null;
        TestContext.setCreatedUserId(createdId);
    }

    @Cuando("obtengo el usuario por su ID")
    public void obtengoElUsuarioPorSuId() {
        String id = TestContext.getCreatedUserId();
        assertNotNull(id, "No hay ID de usuario creado");
        Response response = given()
                .header("X-API-Key", API_KEY)
                .pathParam("id", id)
                .when()
                .get("/users/{id}")
                .then()
                .extract()
                .response();
        TestContext.setLastResponse(response);
    }

    @Cuando("obtengo el usuario con ID {string}")
    public void obtengoElUsuarioConId(String id) {
        Response response = given()
                .header("X-API-Key", API_KEY)
                .pathParam("id", id)
                .when()
                .get("/users/{id}")
                .then()
                .extract()
                .response();
        TestContext.setLastResponse(response);
    }

    @Entonces("la respuesta debe tener código {int}")
    public void laRespuestaDebeTenerCodigo(int codigo) {
        Response response = TestContext.getLastResponse();
        assertNotNull(response, "No hay respuesta almacenada");
        assertEquals(codigo, response.getStatusCode(), "Código HTTP incorrecto");
    }

    @Entonces("el nombre debe ser {string}")
    public void elNombreDebeSer(String nombre) {
        Response response = TestContext.getLastResponse();
        String nameFromGet = response.path("data.name");
        assertEquals(nombre, nameFromGet, "El nombre no coincide");
    }

    @Entonces("el trabajo debe ser {string}")
    public void elTrabajoDebeSer(String trabajo) {
        Response response = TestContext.getLastResponse();
        String jobFromGet = response.path("data.job");
        assertEquals(trabajo, jobFromGet, "El trabajo no coincide");
    }
}
