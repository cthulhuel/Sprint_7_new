import api.client.Courier;
import api.client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class TestLoginCourier {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check success message for correct login and password")
    public void testSuccessMessageForCorrectLoginAndPassword(){
        CourierClient courierClient = new CourierClient();
        Response correctPasswordResponse = courierClient.getCorrectPasswordResponse(new Courier("eliseev_15","qwerty124"));
        correctPasswordResponse.then().statusCode(200).and().assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Check error message for incorrect password")
    public void testErrorMessageForIncorrectPassword(){
        CourierClient courierClient = new CourierClient();
        Response incorrectPasswordResponse = courierClient.getIncorrectPasswordResponse(new Courier("eliseev_15","qwerty123"));
        incorrectPasswordResponse.then().statusCode(404).and().assertThat().body("message", is("Учетная запись не найдена"));
    }
    @Test
    @DisplayName("Check error message for incorrect login")
    public void testErrorMessageForIncorrectLogin(){
        CourierClient courierClient = new CourierClient();
        Response incorrectLoginResponse = courierClient.getIncorrectLoginResponse(new Courier("eliseeev_15","qwerty124"));
        incorrectLoginResponse.then().statusCode(404).and().assertThat().body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Check error message for without login")
    public void testErrorMessageForWithoutLogin(){
        CourierClient courierClient = new CourierClient();
        Response withoutLoginResponse = courierClient.getWithoutLoginResponse(new Courier("","qwerty124"));
        withoutLoginResponse.then().statusCode(400).and().assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check error message for without password")
    public void testErrorMessageForWithoutPassword(){
        CourierClient courierClient = new CourierClient();
        Response withoutPasswordResponse = courierClient.getWithoutPasswordResponse(new Courier("eliseeev_15",""));
        withoutPasswordResponse.then().statusCode(400).and().assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check error message for without login and password")
    public void testErrorMessageForWithoutLoginAndPassword(){
        CourierClient courierClient = new CourierClient();
        Response withoutLoginAndPasswordResponse = courierClient.getWithoutLoginAndPasswordResponse(new Courier("",""));
        withoutLoginAndPasswordResponse.then().statusCode(400).and().assertThat().body("message", is("Недостаточно данных для входа"));
    }

}
