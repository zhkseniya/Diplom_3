package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.PageObject.LoginPage;
import site.nomoreparties.stellarburgers.PageObject.RegistrationPage;

import site.nomoreparties.stellarburgers.Model.User;
import site.nomoreparties.stellarburgers.Model.UserClient;
import site.nomoreparties.stellarburgers.Model.UserCredentials;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

import org.junit.Before;
import org.junit.After;

public class RegistrationTest extends BaseTest {

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
    @DisplayName("Успешная регистрация пользователя с валидными данными")
    @Description("Успешная регистрация пользователя с валидными данными")
    public void checkingSuccessfulRegistration() {

        final String buttonText = "Войти";
        final String buttonLoginText = "Оформить заказ";

        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);

        registrationPage.successfulFillRegistrationForm(user.name, user.email, user.password)
                .clickRegistrationButton();

        LoginPage loginPageForm = page(LoginPage.class);

        Assert.assertTrue("Пользователь не был зарегистрирован", loginPageForm.checkButtonVisible(buttonText));

        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        Assert.assertTrue("Пользователь не был авторизован", $(By.xpath("//button[contains(text(), '" + buttonLoginText + "')]")).shouldBe(visible).exists());
    }

    @Test
    @DisplayName("Ошибка 'Некорректный пароль' при вводе пароля, длиной меньше 6 символов")
    @Description("Регистрация пользователя не возможна, в случае, если ввести пароль, длина которого меньше 6 символов")
    public void checkingRegistrationAnIncorrectPassword() {

        final String buttonText = "Некорректный пароль";

        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);

        registrationPage.successfulFillRegistrationForm(user.name, user.email, RandomStringUtils.randomAlphabetic(3))
                .clickRegistrationButton();

        Assert.assertTrue("Пользователь не был авторизован", $(By.xpath("//p[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists());
    }
}
