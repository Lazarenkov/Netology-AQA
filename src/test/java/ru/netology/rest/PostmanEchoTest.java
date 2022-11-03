package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    @Test
    void shouldReturnSameJSON() {
        Request request = new Request();

        request.setManufacturer("BMW");
        request.setModel("X3");
        request.setProductionYear(2015);
        request.setColor("Белый");
        request.setStateSign("A777AA 77RUS");
        request.setAccidentYears(new int[]{2018, 2020});

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .baseUri("https://postman-echo.com")
        .when()
                .post("/post")
        .then()
                .statusCode(200)
                .assertThat().body("data.manufacturer", equalTo(request.getManufacturer()),
                        "data.model", equalTo(request.getModel()),
                        "data.productionYear", equalTo(request.getProductionYear()),
                        "data.color", equalTo(request.getColor()),
                        "data.stateSign", containsString("777"),
                        "data.accidentYears", hasItems(2020));
    }

}
