package src;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import src.order.OrderApiTest;
import src.order.Order;
import src.user.UserApiTest;
import src.user.User;

public class DataForTest {

    public User validUser;
    public User invalidUser;
    protected UserApiTest userApi;
    protected Order validOrder;
    protected Order invalidOrder;
    protected Order emptyOrder;
    protected OrderApiTest orderApi;

    protected String userAlreadyExists = "User already exists";
    protected String missingMandatoryField = "Email, password and name are required fields";
    protected String failedAuthorization = "email or password are incorrect";
    protected String noIngredientsProvided = "Ingredient ids must be provided";
    protected String invalidIngredientsHash = "One or more ids provided are incorrect";
    protected String nonAuthorization = "You should be authorised";

    String randomEmail = RandomStringUtils.randomAlphabetic(9) + "@example.ru";
    String userName = "userName";
    String password = "password";

    String[] validIngredients = new String[]{
            "61c0c5a71d1f82001bdaaa6c", "61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa76"};
    String[] invalidIngredients = new String[]{
            "invalid hash", "609646e4dc916e00276b2870"};
    String[] emptyIngredients = new String[0];

    @Before
    @Step("Create test data")
    public void setup() {
        validUser = new User(randomEmail, password, userName);
        invalidUser = new User(randomEmail, password);
        userApi = new UserApiTest();

        validOrder = new Order(validIngredients);
        invalidOrder = new Order(invalidIngredients);
        emptyOrder = new Order(emptyIngredients);
        orderApi = new OrderApiTest();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Step("Get access token")
    public String getAccessToken() {
        userApi.registerUser(validUser);
        return userApi.readData();
    }

    @After
    @Step("Clean test data")
    public void cleanData() {
        String data = userApi.readData();
        if (data != null) {
            userApi.deleteUser(userApi.readData());
            userApi.storeData("");
        }
    }
}
