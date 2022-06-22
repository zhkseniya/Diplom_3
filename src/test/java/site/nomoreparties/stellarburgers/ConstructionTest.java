package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import site.nomoreparties.stellarburgers.pageObject.MainPage;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;

public class ConstructionTest extends BaseTest {
    @Before
    public void setUp() {
        setUpYandexDriver();
    }

    @After
    public void tearDown() {
        closeYandexDriver();
    }

    @Test
    @DisplayName("Переход на вкладку 'Соусы'")
    @Description("Переход на вкладку 'Соусы' конструктора, при клике на кнопку 'Соусы'")
    public void switchSauceConstructorTab() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickSauceTab();

        Assert.assertTrue(mainPage.checkCurrentConstructorTabSelected("Соусы"));
    }

    @Test
    @DisplayName("Переход на вкладку 'Булки'")
    @Description("Переход на вкладку 'Булки' конструктора, при клике на кнопку 'Булки'")
    public void switchBunsConstructorTab() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickBunsTab();

        Assert.assertTrue(mainPage.checkCurrentConstructorTabSelected("Булки"));
    }

    @Test
    @DisplayName("Переход на вкладку 'Начинки'")
    @Description("Переход на вкладку 'Начинки' конструктора, при клике на кнопку 'Начинки'")
    public void switchFillingConstructorTab() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickFillingTab();

        Assert.assertTrue(mainPage.checkCurrentConstructorTabSelected("Начинки"));
    }
}
