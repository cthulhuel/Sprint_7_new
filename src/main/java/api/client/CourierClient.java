package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String LOGIN = "/api/v1/courier/login";
    private static final String COURIER = "/api/v1/courier";

    @Step("Get response for correct password")
    public Response getCorrectPasswordResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

    @Step("Get response for incorrect password")
    public Response getIncorrectPasswordResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

    @Step("Get response for incorrect login")
    public Response getIncorrectLoginResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

    @Step("Get response for without login")
    public Response getWithoutLoginResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

    @Step("Get response for without password")
    public Response getWithoutPasswordResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

    @Step("Get response for without login and password")
    public Response getWithoutLoginAndPasswordResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(LOGIN);
    }

}