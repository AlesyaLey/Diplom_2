package src.common;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;

import src.order.Data;
import src.order.OrderApi;
import src.order.Order;
import src.user.UserApi;
import src.user.User;


import static io.restassured.RestAssured.given;
import static src.user.TestUrl.*;

public class BaseTestData {

    public User validUser;
    public User invalidUser;
    public UserApi userApi;
    public Order validOrder;
    public Order invalidOrder;
    public Order emptyOrder;
    public OrderApi orderApi;

    public String userAlreadyExists = "User already exists";
    public String missingMandatoryField = "Email, password and name are required fields";
    public String failedAuthorization = "email or password are incorrect";
    public String noIngredientsProvided = "Ingredient ids must be provided";
    public String invalidIngredientsHash = "One or more ids provided are incorrect";
    public String nonAuthorization = "You should be authorised";

    String randomEmail = RandomStringUtils.randomAlphabetic(9) + "@example.ru";
    String userName = "userName";
    String password = "password";

    //public String[] validIngredients = new String[]{"61c0c5a71d1f82001bdaaa6c", "61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa76"};

   /*  Data data = given()
            .baseUri(baseUrl)
            .basePath(basePathApi)
            .get(getIngredientsUrl)
            .as(Data.class);
*/
     Data data = new OrderApi().takeData();
     String[] validIngredients = new String[]{data.getData().get(0).get_id().toString(), data.getData().get(1).get_id().toString(), data.getData().get(2).get_id().toString()};
     String[] invalidIngredients = new String[]{"invalid hash", "609646e4dc916e00276b2870"};
     String[] emptyIngredients = new String[0];

    @Before
    @Step("Create test data")
    public void setup() {
        System.out.println("Запуск тест ДО");
        validUser = new User(randomEmail, password, userName);
        invalidUser = new User(randomEmail, password);
        userApi = new UserApi();

        validOrder = new Order(validIngredients);
        invalidOrder = new Order(invalidIngredients);
        emptyOrder = new Order(emptyIngredients);
        orderApi = new OrderApi();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Step("Get access token")
    public String getAccessToken() {
        return userApi.getToken(userApi.registerUser(validUser));
    }

    @After
    @Step("Clean test data")
    public void cleanData() {
       String token = userApi.getToken(userApi.loginUser(validUser));
       userApi.deleteUser(token);

    }
}
