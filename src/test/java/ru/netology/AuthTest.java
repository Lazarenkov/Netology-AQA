package ru.netology;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {


    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    @Test
    @DisplayName("Positive test: Valid Login, Valid Password, Valid Status")
    void shouldCreateNewUserWhenAllValid() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("valid", "valid", "valid"))
        .when()
                .post("/api/system/users")
        .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Negative test: Valid Login, Invalid Password, Invalid Status")
    void shouldBeErrorResponseWhenValidLoginInvalidPasswordInvalidStatus() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("valid", "invalid", "invalid"))
        .when()
                .post("/api/system/users")
        .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Negative test: Valid Login, No Password, No Status")
    void shouldBeErrorResponseWhenValidLoginNullPasswordNullStatus() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("valid", "null", "null"))
        .when()
                .post("/api/system/users")
        .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Negative test: Invalid Login, Invalid Password, No Status")
    void shouldBeErrorResponseWhenInvalidLoginInvalidPasswordNullStatus() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("invalid", "invalid", "null"))
        .when()
                .post("/api/system/users")
        .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Negative test: Invalid Login, No Password, Valid Status")
    void shouldBeErrorResponseWhenInvalidLoginNullPasswordValidStatus() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("invalid", "null", "valid"))
        .when()
                .post("/api/system/users")
        .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Negative test: Invalid Login, Valid Password, Invalid Status")
    void shouldBeErrorResponseWhenInvalidLoginValidPasswordInvalidStatus() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("invalid", "valid", "invalid"))
        .when()
                .post("/api/system/users")
        .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Negative test: No Login, No Password, Invalid Status")
    void shouldBeErrorResponseWhenNullLoginNullPasswordInvalidStatus() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("null", "null", "invalid"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Negative test: No Login, Valid Password, No Status")
    void shouldBeErrorResponseWhenNullLoginValidPasswordNullStatus() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("null", "valid", "null"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Negative test: No Login, Invalid Password, Valid Status")
    void shouldBeErrorResponseWhenNullLoginInvalidPasswordValidStatus() {
        given()
                .spec(requestSpec)
                .body(RequestGenerator.generateAuthData("null", "invalid", "valid"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(500);
    }


}
