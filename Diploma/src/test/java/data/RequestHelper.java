package data;

import dto.Dto;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class RequestHelper {

    private RequestHelper() {
    }

    public static ResponseSpecification getApprovedResponseSpec() {
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("status", Matchers.equalTo("APPROVED"))
                .build();
        return responseSpec;
    }

    public static ResponseSpecification getDeclinedResponseSpec() {
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("status", Matchers.equalTo("DECLINED"))
                .build();
        return responseSpec;
    }

    public static ResponseSpecification getError500ResponseSpec() {
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(500)
                .build();
        return responseSpec;
    }

    public static void sendPurchaseRequest(Dto.Request bodyRequest, ResponseSpecification responseSpec) {
        given()
                .contentType(ContentType.JSON)
                .body(bodyRequest)
                .baseUri("http://localhost")
                .port(8080)
        .when()
                .post("api/v1/pay")
        .then()
                .spec(responseSpec);
    }

    public static void sendLoanRequest(Dto.Request bodyRequest, ResponseSpecification responseSpec) {
        given()
                .contentType(ContentType.JSON)
                .body(bodyRequest)
                .baseUri("http://localhost")
                .port(8080)
        .when()
                .post("api/v1/credit")
        .then()
                .spec(responseSpec);
    }

}
