package src.order;

//import io.restassured.RestAssured.g;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import src.user.TestUrl;

import static io.restassured.RestAssured.given;

public class OrderApi implements TestUrl {
    public RequestSpecification request(Order order, String accessToken) {
        return given()
                .spec(spec)
                .header("Authorization", accessToken)
                .body(order);
    }

    public RequestSpecification request(Order order) {
        return given()
                .spec(spec)
                .body(order);
    }

    public RequestSpecification getUsersOrders(String accessToken) {
        return given()
                .spec(spec)
                .header("Authorization", accessToken);
    }

    public RequestSpecification getUsersOrders() {
        return given()
                .spec(spec);
    }

    public Response createOrder(Order order) {
        return request(order).post(createOrderUrl);
    }

    public Response createOrder(Order order, String accessToken) {
        return request(order, accessToken).post(createOrderUrl);
    }

    public Response getUserOrders(String accessToken) {
        return getUsersOrders(accessToken).get(getUsersOrdersUrl);
    }

    public Response getUserOrders() {
        return getUsersOrders().get(getUsersOrdersUrl);
    }


}
