package src;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import src.common.UserSteps;

import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Смена пароля у пользователя")
public class UpdateUserTest extends UserSteps {

    @Test

    @DisplayName("Успешная смена пароля с авторизацией")
    @Description("Регистрация пользователя и смена пароля")
    public void updateUserPasswordTest() {

        createUser(validUser);
        validUser.setPassword("changed_" + validUser.getPassword());
        updateUser(validUser).then().statusCode(200).assertThat()
                .body("success", equalTo(true));

        validUser.setEmail("changed_" + validUser.getEmail());
        updateUser(validUser).then().statusCode(200).assertThat()
                .body("success", equalTo(true));

        validUser.setName("changed_" + validUser.getName());
        updateUser(validUser).then().statusCode(200).assertThat()
                .body("success", equalTo(true));
    }

    @Test

    @DisplayName("Смена пароля без авторизации")
    @Description("Провальная попытка сменить пароль без авторизации пользователя")
    public void updatePasswordNonValidAuthUserTest() {
       updateUser().then().statusCode(401).assertThat()
                .body("success", equalTo(false));
    }
}
