package site.nomoreparties.stellarburgers;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.PageObject.MainPage;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class ConstructionTest extends BaseTest {

    ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\yandexdriver.exe");
        driver = new ChromeDriver();
        setWebDriver(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход по вкладкам 'Булки', 'Соусы', 'Начинки'")
    @Description("Переход по вкладкам конструктора, при клике на кнопки 'Булки', 'Соусы', 'Начинки'")
    public void switchBetweenConstructorTabs() {

        MainPage mainPage = open(MainPage.URL, MainPage.class);


        mainPage.clickSauceTab();
        Assert.assertTrue(mainPage.checkCurrentConstructorTabSelected("Соусы"));


        mainPage.clickBunsTab();
        Assert.assertTrue(mainPage.checkCurrentConstructorTabSelected("Булки"));


        mainPage.clickFillingTab();
        Assert.assertTrue(mainPage.checkCurrentConstructorTabSelected("Начинки"));
    }
}
