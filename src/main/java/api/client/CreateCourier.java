package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateCourier {

    private static final String COURIER = "/api/v1/courier";

    @Step("Get response for repeat login")
    public Response getRepeatLoginResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(COURIER);
    }

    @Step("Get response for correct create courier")
    public Response getCorrectCreateCourierResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(COURIER);
    }

}
