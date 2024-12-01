package src;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Создание пользователя")
public class CreateUserTest extends UserSteps{

    @Test

    @DisplayName("Создать уникального пользователя")
    @Description("Успешное создание пользователя и проверка статуса и имя пользователя")
    public void createNewUserTest() {
        createUser(validUser).then().statusCode(200).assertThat()
                .body("success", equalTo(true));
    }

    @Test

    @DisplayName("Создать пользователя, который уже зарегистрирован")
    @Description("Успешное создание пользователя и проверка на провальное повторное создание такого же")
    public void createDuplicateUserTest() {
        createUser(validUser);
        createUser(validUser).then().statusCode(403).assertThat()
                .body("message", equalTo(userAlreadyExists));
    }

    @Test

    @DisplayName("создать пользователя и не заполнить одно из обязательных полей")
    @Description("Провальное создание пользователя без заполнения Имя. Проверка статуса и сообщения об ошибке")
    public void createNonValidUserTest() {
        createUser(invalidUser).then().statusCode(403).assertThat()
                .body("message", equalTo(missingMandatoryField));
    }
}
