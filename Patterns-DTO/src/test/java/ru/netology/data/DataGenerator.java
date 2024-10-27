package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private DataGenerator() {
    }

    private static final Faker faker = new Faker(new Locale("EN"));



    public static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();

    private static void sendRequest(RegistrationDTO user) {
        given()
                .spec(requestSpec)
                .body(user)
        .when()
                .post("api/system/users")
        .then()
                .statusCode(200);

    }

    public static String getRandomLogin() {
        String login = faker.name().username();
        return login;
    }

    public static String getRandomPassword() {
        String password = faker.internet().password();
        return password;
    }






    public static class Registration{
        private Registration(){}

        public static RegistrationDTO getUser(String status){
            RegistrationDTO user = new RegistrationDTO(getRandomLogin(), getRandomPassword(), status);
            return user;
        }

        public static RegistrationDTO getRegisteredUser(String status){
            RegistrationDTO registeredUser = getUser(status);
            sendRequest(registeredUser);
            return registeredUser;
        }

    }



    @Value
    public static class RegistrationDTO{
            String login;
            String password;
            String status;
    }

}
