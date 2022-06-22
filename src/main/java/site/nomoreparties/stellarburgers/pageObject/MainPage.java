package site.nomoreparties.stellarburgers.pageObject;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    //url станицы "Главная"
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement buttonPersonalArea;

    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement buttonConstruction;

    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement buttonLogo;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement buttonLogin;

    // -------- Секция "Соберите бургер" --------------
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement spanBunsTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement spanSauceTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement spanFillingTab;
    // -------- END Секция "Соберите бургер" ----------

    // метод, кликающий по табу "Булки" в конструкторе
    @Step("Кликает по табу 'Булки'")
    public void clickBunsTab() {
        spanBunsTab.shouldBe(visible).doubleClick();
    }

    // метод, кликающий по табу "Соусы" в конструкторе
    @Step("Кликает по табу 'Соусы'")
    public void clickSauceTab() {
        spanSauceTab.shouldBe(visible).click();
    }

    // метод, кликающий по табу "Начинки" в конструкторе
    @Step("Кликает по табу 'Начинки'")
    public void clickFillingTab() {
        spanFillingTab.shouldBe(visible).click();
    }

    // метод, проверяющий, что активен таб, по которому кликнули
    public boolean checkCurrentConstructorTabSelected(String tabName) {
        return $(By.xpath("//div[contains(@class, 'current') and //span[contains(text(), '" + tabName + "')]]")).exists();
    }

    // метод, кликающий по кнопке "Личный кабинет" в шапке
    @Step("Кликает по кнопке 'Личный кабинет'")
    public MainPage clickPersonalAreaButtonInHeader() {
        buttonPersonalArea.click();
        return this;
    }

    // метод, кликающий по кнопке "Войти в аккаунт" в шапке
    @Step("Кликает по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        buttonLogin.click();
    }

    // метод, проверяющий, что отображается нужная кнопка на странице
    @Step("Проверяет текст на кнопке")
    public boolean checkButtonVisible(String buttonText) {
        return $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists();
    }

    // метод, проверяющий, что отображается нужный заголовок на странице
    @Step("Проверяет заголовок на странице")
    public boolean checkHeaderVisible(String headerText) {
        return $(By.xpath("//h1[contains(text(), '" + headerText + "')]")).shouldBe(visible).exists();
    }
}
