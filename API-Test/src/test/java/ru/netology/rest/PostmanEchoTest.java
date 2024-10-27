package ru.netology.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {
    Request request = new Request();

    @BeforeEach
    void setRequest() throws JsonProcessingException {
        request.setManufacturer("BMW");
        request.setModel("X3");
        request.setProductionYear(2015);
        request.setColor("Белый");
        request.setStateSign("A777AA 77RUS");
        request.setAccidentYears(new int[]{2018, 2020});

//       ObjectMapper mapper = new ObjectMapper();
//    String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
//    System.out.println(prettyJson);
    }

    @Test
    void shouldReturnCorrectFields() {
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

    @Test
    void shouldMatchSchema() throws JsonProcessingException {
        given()
                .contentType(ContentType.JSON)
                .body(request)
                .baseUri("https://postman-echo.com")
        .when()
                .post("/post")
        .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
    }

}
