package site.nomoreparties.stellarburgers.Model;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {
    private static final String AUTH_PATH = "api/auth/";

    @Step("Send POST request to api/auth/register/")
    public static Response registrationUser(User user) {
        Response response = given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(AUTH_PATH + "register/");
        return response;
    }

    @Step("Send POST request to api/auth/login/")
    public static Response loginUser(UserCredentials credentials) {
        Response response = given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(AUTH_PATH + "login/");
        return response;
    }

    @Step("Send DELETE request to api/auth/user/")
    public static Response deleteUser(String userToken) {
        Response response = given()
                .spec(getBaseSpec())
                .header("Authorization", userToken)
                .when()
                .delete(AUTH_PATH + "user/");
        return response;
    }
}
