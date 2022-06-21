package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.PageObject.MainPage;
import site.nomoreparties.stellarburgers.PageObject.LoginPage;

import site.nomoreparties.stellarburgers.Model.User;
import site.nomoreparties.stellarburgers.Model.UserClient;
import site.nomoreparties.stellarburgers.Model.UserCredentials;

import org.junit.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

import org.junit.Before;
import org.junit.After;
import site.nomoreparties.stellarburgers.PageObject.PersonalAreaPage;

public class HeaderButtonTest extends BaseTest {

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
    @DisplayName("Переход в личный кабинет")
    @Description("Авторизованный пользователь может перейти в личный кабинет по кнопке 'Личный кабинет'")
    public void checkingUserCanGoToPersonalAreaByClickToPersonalAreaButton() {

        final String buttonText = "Выход";

        LoginPage loginPageForm = open(LoginPage.URL, LoginPage.class);
        loginPageForm.successfulFillLoginForm(user.email, user.password)
                .clickLoginButton();

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButtonInHeader();

        Assert.assertTrue("Пользователь не перешел в личный кабинет", $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists());

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

        Assert.assertTrue("Пользователь не перешел на главную страницу", $(By.xpath("//h1[contains(text(), '" + headerText + "')]")).shouldBe(visible).exists());

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

        Assert.assertTrue("Пользователь не перешел на главную страницу", $(By.xpath("//h1[contains(text(), '" + headerText + "')]")).shouldBe(visible).exists());

    }
}
