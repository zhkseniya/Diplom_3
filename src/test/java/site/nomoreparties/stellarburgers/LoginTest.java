package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import site.nomoreparties.stellarburgers.PageObject.MainPage;
import site.nomoreparties.stellarburgers.PageObject.LoginPage;
import site.nomoreparties.stellarburgers.PageObject.RegistrationPage;
import site.nomoreparties.stellarburgers.PageObject.ForgotPasswordPage;

import site.nomoreparties.stellarburgers.Model.User;
import site.nomoreparties.stellarburgers.Model.UserClient;
import site.nomoreparties.stellarburgers.Model.UserCredentials;

import com.codeborne.selenide.WebDriverRunner;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import org.junit.Before;
import org.junit.After;

public class LoginTest extends BaseTest {

    private UserClient userClient;
    private User user;
    private String userToken;
    ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\yandexdriver.exe");
        driver = new ChromeDriver();
        setWebDriver(driver);

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

        driver.quit();
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

        Assert.assertTrue("Пользователь не был авторизован", $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists());
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

        Assert.assertTrue("Пользователь не был авторизован", $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists());
    }
}
