package com.miproyecto.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class ReqresApiTest {

    private static final String BASE_URI = "https://reqres.in/api";
    private static final String API_KEY = "reqres_f223f2d1fa1f4ea69720b7956c5b8441";
    private static final String USER_NAME = "Test User";
    private static final String USER_JOB = "Automation Engineer";

    @BeforeAll
    public static void setUpApi() {
        RestAssured.baseURI = BASE_URI;
    }
    // Helpers reutilizables entre tests
    private Response crearUsuario() {
        String requestBody = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", USER_NAME, USER_JOB);

        return given()
            .contentType(ContentType.JSON)
            .header("X-API-Key", API_KEY)
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .extract()
            .response();
    }

    private Response obtenerUsuarioPorId(String id) {
        return given()
            .header("X-API-Key", API_KEY)
            .pathParam("id", id)
        .when()
            .get("/users/{id}")
        .then()
            .extract()
            .response();
    }

    @Test
    @DisplayName("POST /users - Debe devolver 201 con el usuario creado")
    public void postUsersDebeDevolver201() {
        Response postResponse = crearUsuario();

        System.out.println("Status code POST /users: " + postResponse.getStatusCode());
        System.out.println("Body POST /users: " + postResponse.getBody().asString());

        assertEquals(201, postResponse.getStatusCode(), "El POST /users debe devolver HTTP 201");
    }

    @Test
    @DisplayName("GET /users/{id} - Debe devolver 200 con el id creado")
    public void getUserByIdDebeDevolver200() {
        // Primero creamos un usuario para obtener un id válido
        Response postResponse = crearUsuario();
        String createdId = String.valueOf(postResponse.path("id"));

        Response getResponse = obtenerUsuarioPorId(createdId);

        System.out.println("Status code GET /users/{id}: " + getResponse.getStatusCode());
        System.out.println("Body GET /users/{id}: " + getResponse.getBody().asString());

        assertEquals(200, getResponse.getStatusCode(), "El GET /users/{id} debe devolver HTTP 200");
    }

    @Test
    @DisplayName("GET /users/{id} - Name y Job deben coincidir con POST")
    public void getUserByIdDebeMantenerNombreYTrabajo() {
        // Creamos usuario y luego lo consultamos
        Response postResponse = crearUsuario();
        String createdId = String.valueOf(postResponse.path("id"));

        Response getResponse = obtenerUsuarioPorId(createdId);

        String nameFromGet = getResponse.path("data.name");
        String jobFromGet = getResponse.path("data.job");

        System.out.println("Nombre desde GET: " + nameFromGet);
        System.out.println("Job desde GET: " + jobFromGet);

        assertEquals(
            USER_NAME,
            nameFromGet,
            "El nombre del usuario obtenido debe coincidir con el enviado en el POST"
        );
        assertEquals(
            USER_JOB,
            jobFromGet,
            "El trabajo del usuario obtenido debe coincidir con el enviado en el POST"
        );
    }

    @Test
    @DisplayName("GET /users/1 - Debe devolver 200 con el id 1")
    public void getUserId1DebeDevolver200() {
        Response getResponse = obtenerUsuarioPorId("1");

        System.out.println("Status code GET /users/1: " + getResponse.getStatusCode());
        System.out.println("Body GET /users/1: " + getResponse.getBody().asString());

        assertEquals(200, getResponse.getStatusCode(), "El GET /users/1 debe devolver HTTP 200");
    }


}

