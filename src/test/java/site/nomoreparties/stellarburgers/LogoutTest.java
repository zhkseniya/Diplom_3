package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import site.nomoreparties.stellarburgers.pageObject.MainPage;
import site.nomoreparties.stellarburgers.pageObject.LoginPage;
import site.nomoreparties.stellarburgers.pageObject.PersonalAreaPage;
import site.nomoreparties.stellarburgers.model.User;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.utils.UserCredentials;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import org.junit.Before;
import org.junit.After;

public class LogoutTest extends BaseTest {
    private UserClient userClient;
    private User user;
    private String userToken;

    @Before
    public void setUp() {
        setUpYandexDriver();

        user = User.getRandom();
        userClient = new UserClient();
        userClient.registrationUser(user);
    }

    @After
    public void tearDown() {
        int statusCode = userClient.loginUser(UserCredentials.from(user)).statusCode();
        if(statusCode == 200) {
            userToken = userClient.loginUser(UserCredentials.from(user)).body().path("accessToken").toString();
            userClient.deleteUser(userToken);
        }

        closeYandexDriver();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Авторизованный пользователь может выйти из аккаунта по кнопке 'Выход', в личном кабинете")
    public void checkingLogoutPersonalArea() {
        final String buttonText = "Войти";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButtonInHeader();

        PersonalAreaPage personalAreaPage = page(PersonalAreaPage.class);
        personalAreaPage.clickLogoutButton();

        Assert.assertTrue("Пользователь не вышел из аккаунта", loginPageForm.checkButtonVisible(buttonText));
    }
}
