import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class testCreateCourier {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    /*
        @Test
        public void createCourierNew(){
    //        File json = new File("src/main/resources/create.json");
            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .and()
                            .body(" { \"login\": \"\", \"password\": \"qwerty124\" } ")
                            .when()
                            .post("/api/v1/courier");
            response.then().assertThat().body("ok", equalTo(true))
                    .and().statusCode(201);
        }
    */
    @Test
    public void createCourierRepeatLogin(){
        File json = new File("src/main/resources/create.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and().statusCode(409);
    }

    @Test
    public void createCourierWithoutLogin(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(" { \"login\": \"\", \"password\": \"qwerty124\" } ")
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and().statusCode(400);
    }

    @Test
    public void createCourierWithoutPassword(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(" { \"login\": \"eliseev_15\", \"password\": \"\" } ")
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and().statusCode(400);
    }

    @Test
    public void createCourierWithoutLoginAndPassword(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(" { \"login\": \"\", \"password\": \"\" } ")
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and().statusCode(400);
    }

}
