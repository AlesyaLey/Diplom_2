package src.common;

import src.user.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserSteps extends BaseTestData {

    @Step("Create user")
    public Response createUser(User user) {
        return userApi.registerUser(user);
    }

    @Step("Login user")
    public Response loginUser(User user) {
        return userApi.loginUser(user);
    }

    @Step("Update user")
    public Response updateUser(User user) {
        return userApi.updateUser(user, userApi.readData());
    }

    @Step("Update user without auth")
    public Response updateUser() {
        return userApi.updateUser(validUser);
    }
}