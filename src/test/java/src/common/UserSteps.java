package src.common;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
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
    //старый вариант
    public Response updateUser(User user, String token) {return userApi.updateUser(user, token);}

    @Step("get token")
    public String getToken(Response response){return userApi.getToken(response);}

    /*
    public Response updateUser(User user) {
        String accessToken = userApi.updateUser(user).then().extract().body().path("accessToken").toString();
        return userApi.updateUser(user, accessToken);
        //return userApi.updateUser(user);
    }
*/

    @Step("Update user without auth")
    public Response updateUser() {
        return userApi.updateUser(validUser);
    }
}