import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestOrderList {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void testOrderList(){
        Response response =
                given()
                        .get("/api/v1/orders");
        response.then().assertThat().body(notNullValue())
                .and().statusCode(200);
    }

}
