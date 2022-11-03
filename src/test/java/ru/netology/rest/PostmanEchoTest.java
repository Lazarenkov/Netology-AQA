package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    @Test
    void shouldReturnSameJSON() {

        given()
                .body("some data")
                .baseUri("https://postman-echo.com")
        .when()
                .post("/post")
        .then()
                .statusCode(200)
                .assertThat().body("data", equalTo("some data"));
    }

}
