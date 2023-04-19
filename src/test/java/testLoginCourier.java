import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class testLoginCourier {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void loginCourier(){
        File json = new File("src/main/resources/login.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("id", notNullValue())
                .and().statusCode(200);
    }

    @Test
    public void loginCourierWithoutLogin(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(" { \"login\": \"\", \"password\": \"qwerty124\" } ")
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and().statusCode(400);
    }

    @Test
    public void loginCourierWithoutPassword(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(" { \"login\": \"eliseev_15\", \"password\": \"\" } ")
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and().statusCode(400);
    }

    @Test
    public void loginCourierWithoutLoginAndPassword(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(" { \"login\": \"\", \"password\": \"\" } ")
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and().statusCode(400);
    }

    @Test
    public void loginCourierInvalidLogin(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(" { \"login\": \"eliseeev_15\", \"password\": \"qwerty124\" } ")
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and().statusCode(404);
    }

    @Test
    public void loginCourierInvalidPassword(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(" { \"login\": \"eliseev_15\", \"password\": \"qwerty123\" } ")
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and().statusCode(404);
    }

}
