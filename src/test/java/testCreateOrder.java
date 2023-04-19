import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)

public class testCreateOrder {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String color;

    public testCreateOrder (
            String firstName,
            String lastName,
            String address,
            String metroStation,
            String phone,
            String rentTime,
            String deliveryDate,
            String comment,
            String color
    )
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] enterData() {
        return new Object[][] {
                { "Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", "5", "2020-06-06", "Saske, come back to Konoha", "BLACK"},
                { "Арнольд", "Шварценеггер", "Калифорния", "1", "+7 632 777 01 10", "2", "2020-07-07", "i will be back", "GREY"},
                { "Джеки", "Чан", "Гонконг", "2", "+7 632 777 01 10", "3", "2021-01-01", "не боец, который умеет играть", ""},
                { "Уиллес", "Брюс", "Оберштайн", "6", "+7 632 777 01 10", "7", "2022-02-02", "Больше никогда", "BLACK, GREY"},

        };
    }


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void createOrder(){
        File json = new File("src/test/resources/order.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
//                        .body(json)
//                      .body(" { \"firstName\": \""+firstName+"\"" +
                        .body(" { \"firstName\": \"" + firstName + "\",\n" +
                                "    \"lastName\": \"" + lastName + "\",\n" +
                                "    \"address\": \"" + address + "\",\n" +
                                "    \"metroStation\": " + metroStation + ",\n" +
                                "    \"phone\": \"" + phone + "\",\n" +
                                "    \"rentTime\": " + rentTime + ",\n" +
                                "    \"deliveryDate\": \"" + deliveryDate + "\",\n" +
                                "    \"comment\": \"" + comment + "\",\n" +
                                "    \"color\": [\"" + color + "\"]\n" +
                                "}")
                        .when()
                        .post("/api/v1/orders");
        response.then().assertThat().body("track", notNullValue())
                .and().statusCode(201);

    }

}
