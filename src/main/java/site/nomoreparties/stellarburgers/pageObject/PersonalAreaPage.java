package site.nomoreparties.stellarburgers.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAreaPage {
    //url станицы
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement buttonLogout;

    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement buttonConstruction;

    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement buttonLogo;

    // метод, кликающий по "Выход"
    @Step("Кликает по кнопке 'Выход'")
    public void clickLogoutButton() {
        buttonLogout.shouldBe(visible).click();
    }

    // метод, кликающий по "Конструктор"
    @Step("Кликает по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        buttonConstruction.shouldBe(visible).click();
    }

    // метод, кликающий по "Логотипу"
    @Step("Кликает по Логотипу")
    public void clickLogoButton() {
        buttonLogo.shouldBe(visible).click();
    }

    // метод, проверяющий, что кнопка активна
    @Step("Проверяет текст на кнопке")
    public boolean checkCurrentPersonalAreaButton(String buttonText) {
        return $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists();
    }
}
