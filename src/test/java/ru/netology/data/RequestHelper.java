package ru.netology.data;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class RequestHelper {

    private RequestHelper(){}

    public static String token;

    public static void sendAuthRequest() {

        given()
                .contentType(ContentType.JSON)
                .body(DataHelper.getLoginDTO())
                .baseUri("http://localhost")
                .port(9999)
        .when()
                .post("api/auth")
        .then()
                .statusCode(200);

    token = given()
                .contentType(ContentType.JSON)
                .body(DataHelper.getVerificationDTO())
                .baseUri("http://localhost")
                .port(9999)
        .when()
                .post("api/auth/verification")
        .then()
                .statusCode(200)
                .extract().path("token");
    }

    public static void sendTransferRequest(String from, String to, int value) {
        given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(DataHelper.getTransferDTO(from, to, value))
                .baseUri("http://localhost")
                .port(9999)
        .when()
                .post("api/transfer")
        .then()
                .statusCode(200);
    }

}
