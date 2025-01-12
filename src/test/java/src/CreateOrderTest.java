package src;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import src.common.OrderSteps;

import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Создание заказа")
public class CreateOrderTest  extends OrderSteps {


    @Test

    @DisplayName("Успешное создание заказа авторизованным пользователем")
    @Description("Успешная регистрация пользователя, проверка успешно созданного заказа и авторизованого пользователя")
    public void createValidAuthOrderTest() {
        createOrder(validOrder, getAccessToken()).then().statusCode(200).assertThat()
                .body("success", equalTo(true)).and()
                .body("order.owner.name", equalTo(validUser.getName()));
    }

    @Test

    @DisplayName("Успешное создание заказа пользователем без авторизации")
    @Description("Создание заказа, проверка статуса заказа и проверка пользователя")
    public void createNonValidAuthOrderTest() {
        createOrder(validOrder).then().statusCode(200).assertThat()
                .body("success", equalTo(true)).and()
                .body("order.owner.name", equalTo(null));
    }

    @Test

    @DisplayName("Провальное создание заказа без ингридиентов")
    @Description("Создание заказа, проверка статуса заказа и проверка сообщения об отсутвии ингридиентов")
    public void createWithoutIngredientsOrderTest() {
        createOrder(emptyOrder).then().statusCode(400).assertThat()
                .body("success", equalTo(false)).and()
                .body("message", equalTo(noIngredientsProvided));
    }

    @Test

    @DisplayName("Создание заказа с  неверным хешем ингредиентов")
    @Description("Создание заказа с несуществующим ингридиентом и проверка сообщения")
    public void createWrongIngredientsOrderTest() throws InterruptedException {
        Thread.sleep(500);
        createOrder(invalidOrder).then().statusCode(400).assertThat()
                .body("success", equalTo(false)).and()
                .body("message", equalTo(invalidIngredientsHash));
    }
}
