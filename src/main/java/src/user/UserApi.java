package src.user;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.*;

import static src.user.TestUrl.*;
//public class UserApi implements TestUrl {
public class UserApi {

    public RequestSpecification request(User user, String accessToken) {
        return RestAssured
                .given()
                .spec(spec)
                .header("Authorization", accessToken)
                .body(user);
    }

    public RequestSpecification request(User user) {
        return RestAssured
                .given()
                .spec(spec)
                .body(user);
    }

    public RequestSpecification request(String accessToken) {
        return RestAssured
                .given()
                .spec(spec)
                .header("Authorization", accessToken);
    }



    public Response registerUser(User user) {
        Response response = request(user).post(registerUserUrl);
        if (String.valueOf(response.statusCode()).equals("200")) {
            String accessToken = response.then().extract().body().path("accessToken").toString();
           // storeData(accessToken);
        }
        return response;
    }

    public Response loginUser(User user) {
        Response response = request(user).post(loginUserUrl);
         if (String.valueOf(response.statusCode()).equals("200")) {
             String accessToken = response.then().extract().body().path("accessToken").toString();
            //storeData(accessToken);
        }
        return response;
    }

    public void deleteUser(String accessToken) {
        request(accessToken).delete(deleteUserUrl);
    }

    public Response updateUser(User user, String accessToken) {
        return request(user, accessToken).patch(updateUserUrl);
    }

    public Response updateUser(User user) {
        return request(user).patch(updateUserUrl);
    }

    public Response getUser() {
        return request(new User()).get(getUserUrl);
    }
/*
    public void storeData(String string) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataForTest));
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataForTest));
            String token = reader.readLine();
            reader.close();
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
public String readData(User user) {
    Response response = request(user).patch(updateUserUrl);
    String accessToken = response.then().extract().body().path("accessToken").toString();



    //response = request(user).patch(updateUserUrl);
   /* String accessToken="";
    if (String.valueOf(response.statusCode()).equals("200")) {
         accessToken = response.then().extract().body().path("accessToken").toString();
        //storeData(accessToken);
    }*/


    System.out.println("Save token "+ accessToken);
    return accessToken;
    }
    public String getToken(Response response){

        if(String.valueOf(response.statusCode()).equals("200")) {
            String token = response.then().extract().body().path("accessToken").toString();
            return token;}
        else return "";
    }



}
