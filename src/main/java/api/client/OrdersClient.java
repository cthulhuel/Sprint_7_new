package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrdersClient {

    private static final String ORDERS = "/api/v1/orders";

    @Step("Get response for create order")
    public Response getCreateOrderResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(ORDERS);
    }

}
