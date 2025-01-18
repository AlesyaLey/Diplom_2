package src;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import src.common.UserSteps;

import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Логин пользователя")
public class LoginUserTest extends UserSteps {

    @Test

    @DisplayName("Логин под существующим пользователем")
    @Description("Успешная регистрация нового пользоватея и авторизация")
    public void logInRegisteredUserTest() {
        createUser(validUser);
        loginUser(validUser).then().statusCode(200).assertThat()
                .body("success", equalTo(true));

        String token = loginUser(validUser).then().extract().body().path("accessToken").toString();
        System.out.println(" token hjfbjdf  "+token);

        String ttttoken = getToken(loginUser(validUser));
        System.out.println(" Получен токен через запрос  " +ttttoken);
    }

    @Test

    @DisplayName("Логин с неверным логином")
    @Description("Провальный вход под незарегистрированным пользователем")
    public void logInUnregisteredUserTest() {
        loginUser(invalidUser).then().statusCode(401).assertThat()
                .body("message", equalTo(failedAuthorization));
    }

}
