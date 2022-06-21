package site.nomoreparties.stellarburgers.PageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    //url станицы
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    // -------- Форма "Вход"  ----------
    @FindBy(how = How.XPATH, using = ".//label[text() ='Email']/following::input[1]")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = ".//label[text() ='Пароль']/following::input[1]")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement buttonLogin;

    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;

    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement buttonForgotPassword;
    // -------- END Форма "Вход" ----------

    // метод, кликающий по "Войти"
    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        buttonLogin.click();
    }

    // метод, кликающий по "Зарегистрироваться"
    public LoginPage clickRegistrationButton() {
        buttonRegistration.click();
        return this;
    }

    // метод, кликающий по "Восстановить пароль"
    public LoginPage clickForgotPasswordButton() {
        buttonForgotPassword.click();
        return this;
    }

    // метод для заполнения элемента формы нужным значением
    private void fillInTheField(SelenideElement formElement, String value) {
        formElement.sendKeys(value);
    }

    // метод "заполняющий" форму входа
    @Step("Заполняет форму входа")
    public LoginPage successfulFillLoginForm(String email, String password) {
        fillInTheField(inputEmail, email);
        fillInTheField(inputPassword, password);
        return this;
    }

    // метод, проверяющий, что активен таб, по которому кликнули
    public boolean checkButtonVisible(String buttonText) {
        return $(By.xpath("//button[contains(text(), '" + buttonText + "')]")).shouldBe(visible).exists();
    }
}
