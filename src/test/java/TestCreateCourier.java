import api.client.CourierClient;
import api.client.CreateCourier;
import api.client.NewCourier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class TestCreateCourier {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check message for create courier repeat login")
    public void testMessageForCreateCourierRepeatLogin(){
        CreateCourier createCourier = new CreateCourier();
        Response correctRepeatLoginResponse = createCourier.getRepeatLoginResponse(new NewCourier("eliseev_15","qwerty124", "john"));
        correctRepeatLoginResponse.then().statusCode(409).and().assertThat().body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Check message for create courier repeat login")
    public void testMessageForCreateCourierWithoutLogin(){
        CourierClient courierClient = new CourierClient();
        Response correctRepeatLoginResponse = courierClient.getWithoutLoginResponse(new NewCourier("","qwerty124", "john"));
        correctRepeatLoginResponse.then().statusCode(400).and().assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check message for create courier repeat login")
    public void testMessageForCreateCourierWithoutPassword(){
        CourierClient courierClient = new CourierClient();
        Response correctRepeatLoginResponse = courierClient.getWithoutPasswordResponse(new NewCourier("","", "john"));
        correctRepeatLoginResponse.then().statusCode(400).and().assertThat().body("message", is("Недостаточно данных для входа"));
    }

}
