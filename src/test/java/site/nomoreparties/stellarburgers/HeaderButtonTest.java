package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import site.nomoreparties.stellarburgers.pageObject.MainPage;
import site.nomoreparties.stellarburgers.pageObject.LoginPage;
import site.nomoreparties.stellarburgers.model.User;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.utils.UserCredentials;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import org.junit.Before;
import org.junit.After;
import site.nomoreparties.stellarburgers.pageObject.PersonalAreaPage;

public class HeaderButtonTest extends BaseTest {
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
    @DisplayName("Переход в личный кабинет")
    @Description("Авторизованный пользователь может перейти в личный кабинет по кнопке 'Личный кабинет'")
    public void checkingUserCanGoToPersonalAreaByClickToPersonalAreaButton() {
        final String buttonText = "Выход";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButtonInHeader();

        PersonalAreaPage personalAreaPage = page(PersonalAreaPage.class);

        Assert.assertTrue("Пользователь не перешел в личный кабинет", personalAreaPage.checkCurrentPersonalAreaButton(buttonText));
    }

    @Test
    @DisplayName("Переход в конструктор по клику на кнопку 'Конструктор' в шапке сайта")
    @Description("Авторизованный пользователь может перейти в конструктор, кликнув по кнопке 'Конструктор', в шапке сайта")
    public void checkingUserCanGoToMainPageByClickToConstructorButton() {
        final String headerText = "Соберите бургер";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButtonInHeader();

        PersonalAreaPage personalAreaPage = page(PersonalAreaPage.class);
        personalAreaPage.clickConstructorButton();

        Assert.assertTrue("Пользователь не перешел на главную страницу", mainPage.checkHeaderVisible(headerText));
    }

    @Test
    @DisplayName("Переход в конструктор по клику на 'Логотип' в шапке сайта")
    @Description("Авторизованный пользователь может перейти в конструктор, кликнув по логотипу, в шапке сайта")
    public void checkingUserCanGoToMainPageByClickToLogo() {
        final String headerText = "Соберите бургер";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButtonInHeader();

        PersonalAreaPage personalAreaPage = page(PersonalAreaPage.class);
        personalAreaPage.clickLogoButton();

        Assert.assertTrue("Пользователь не перешел на главную страницу", mainPage.checkHeaderVisible(headerText));
    }
}
