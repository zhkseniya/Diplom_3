package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import site.nomoreparties.stellarburgers.pageObject.MainPage;
import site.nomoreparties.stellarburgers.pageObject.LoginPage;
import site.nomoreparties.stellarburgers.pageObject.RegistrationPage;
import site.nomoreparties.stellarburgers.pageObject.ForgotPasswordPage;
import site.nomoreparties.stellarburgers.model.User;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.utils.UserCredentials;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import org.junit.Before;
import org.junit.After;

public class LoginTest extends BaseTest {
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
    @DisplayName("Переход к авторизации при клике на кнопку 'Войти в аккаунт' на главной")
    @Description("Пользователь может войти в свой аккаунт, кликнув на кнопку 'Войти в аккаунт' на главной")
    public void checkingLoginByClickOnTheButtonOnTheMainPage() {
        final String buttonText = "Оформить заказ";

        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();

        LoginPage loginPageForm = page(LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        Assert.assertTrue("Пользователь не был авторизован", mainPage.checkButtonVisible(buttonText));
    }

    @Test
    @DisplayName("Переход к авторизации при клике на кнопку 'Личный кабинет' на главной")
    @Description("Пользователь может войти в свой аккаунт, кликнув на кнопку 'Личный кабинет' на главной")
    public void checkingLoginThroughPersonalArea() {
        final String buttonText = "Оформить заказ";

        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickPersonalAreaButtonInHeader();

        LoginPage loginPageForm = page(LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        Assert.assertTrue("Пользователь не был авторизован", mainPage.checkButtonVisible(buttonText));
    }

    @Test
    @DisplayName("Переход к авторизации при клике на кнопку 'Войти' на странице регистрации")
    @Description("Пользователь может войти в свой аккаунт, кликнув на кнопку 'Войти' на странице регистрации")
    public void checkingLoginFromRegistrationPage() {
        final String buttonText = "Оформить заказ";

        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        registrationPage.clickLoginButton();

        LoginPage loginPageForm = page(LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        MainPage mainPage = page(MainPage.class);

        Assert.assertTrue("Пользователь не был авторизован", mainPage.checkButtonVisible(buttonText));
    }

    @Test
    @DisplayName("Переход к авторизации при клике на кнопку 'Войти' на странице восстановления пароля")
    @Description("Пользователь может войти в свой аккаунт, кликнув на кнопку 'Войти' на странице восстановления пароля")
    public void checkingLoginFromForgotPasswordPage() {
        final String buttonText = "Оформить заказ";

        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.URL, ForgotPasswordPage.class);
        forgotPasswordPage.clickLoginButton();

        LoginPage loginPageForm = page(LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        MainPage mainPage = page(MainPage.class);

        Assert.assertTrue("Пользователь не был авторизован", mainPage.checkButtonVisible(buttonText));
    }
}
