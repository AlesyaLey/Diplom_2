package src;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import src.common.OrderSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.empty;

@DisplayName("Получение заказов конкретного пользователя")

public class GetUserOrderTest extends OrderSteps {

    @Test

    @DisplayName("Успешное получение заказов авторизованного пользователя")
    @Description("Успешная регистрация пользователя, получение заказа и проверка, что лист пустой")
    public void getUsersOrdersWithAuth() {
        getOrders(getAccessToken()).then().statusCode(200).assertThat()
                .body("success", equalTo(true)).and()
                .body("orders", empty());
    }

    @Test

    @DisplayName("Проверка что нельзя получить заказ неавторизованному пользователю")
    @Description("Провальная попытка сделать заказа без авторизации и проверка сообщения об ошибки")
    public void getUsersOrdersWithNoAuth() {
        getOrders().then().statusCode(401).assertThat()
                .body("success", equalTo(false)).and()
                .body("message", equalTo(nonAuthorization));
    }
}
