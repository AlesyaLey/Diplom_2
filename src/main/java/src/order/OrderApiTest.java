package src.order;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import src.user.TestUrl;

public class OrderApiTest implements TestUrl {
    public RequestSpecification request(Order order, String accessToken) {
        return RestAssured
                .given()
                .spec(spec)
                .header("Authorization", accessToken)
                .body(order);
    }

    public RequestSpecification request(Order order) {
        return RestAssured
                .given()
                .spec(spec)
                .body(order);
    }

    public RequestSpecification getUsersOrders(String accessToken) {
        return RestAssured
                .given()
                .spec(spec)
                .header("Authorization", accessToken);
    }

    public RequestSpecification getUsersOrders() {
        return RestAssured
                .given()
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
