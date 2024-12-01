package src;

import src.order.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class OrderSteps extends DataForTest{

    @Step("Create order")
    public Response createOrder(Order order) {
        return orderApi.createOrder(order);
    }

    @Step("Create order")
    public Response createOrder(Order order, String accessToken) {
        return orderApi.createOrder(order, accessToken);
    }

    @Step("Get user's orders")
    public Response getOrders(String accessToken) {
        return orderApi.getUserOrders(accessToken);
    }

    @Step("Get user's orders")
    public Response getOrders() {
        return orderApi.getUserOrders();
    }
}
