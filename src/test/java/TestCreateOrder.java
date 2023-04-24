import api.client.Order;
import api.client.OrdersClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)

public class TestCreateOrder {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String rentTime;
    private final String deliveryDate;
    private final String comment;
    private List<String> color;

    public TestCreateOrder(
            String firstName,
            String lastName,
            String address,
            String metroStation,
            String phone,
            String rentTime,
            String deliveryDate,
            String comment,
            List<String> color
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

    @Parameterized.Parameters(name = "Успешный заказ. Тестовые данные: {0} {1} {2} {3}")
    public static Object[][] enterData() {
        return new Object[][] {
                { "Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", "5", "2020-06-06", "Saske, come back to Konoha", Arrays.asList("BLACK")},
                { "Арнольд", "Шварценеггер", "Калифорния", "1", "+7 632 777 01 10", "2", "2020-07-07", "i will be back", Arrays.asList("GREY")},
                { "Джеки", "Чан", "Гонконг", "2", "+7 632 777 01 10", "3", "2021-01-01", "не боец, который умеет играть", Arrays.asList("")},
                { "Уиллес", "Брюс", "Оберштайн", "6", "+7 632 777 01 10", "7", "2022-02-02", "Больше никогда", Arrays.asList("BLACK, GREY")},

        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check success create order")
    public void testSuccessCreateOrder(){
        OrdersClient ordersClient = new OrdersClient();
        Response correctCreateOrderResponse = ordersClient.getCreateOrderResponse(
                new Order());
        correctCreateOrderResponse.then().statusCode(201).and().assertThat().body("track", notNullValue());
    }

}
