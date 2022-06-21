package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.PageObject.MainPage;
import site.nomoreparties.stellarburgers.PageObject.LoginPage;
import site.nomoreparties.stellarburgers.PageObject.PersonalAreaPage;

import site.nomoreparties.stellarburgers.Model.User;
import site.nomoreparties.stellarburgers.Model.UserClient;
import site.nomoreparties.stellarburgers.Model.UserCredentials;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

import org.junit.Before;
import org.junit.After;

public class LogoutTest extends BaseTest {

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
