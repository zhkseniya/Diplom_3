package site.nomoreparties.stellarburgers.PageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    //url станицы
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    // -------- Форма "Регистрация"  ----------
    @FindBy(how = How.XPATH, using = ".//label[text() ='Имя']/following::input[1]")
    private SelenideElement inputName;

    @FindBy(how = How.XPATH, using = ".//label[text() ='Email']/following::input[1]")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = ".//label[text() ='Пароль']/following::input[1]")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement buttonLogin;

    @FindBy(how = How.XPATH, using = ".//p[@class='input__error' and text()='Некорректный пароль']")
    private SelenideElement errorMessage;
    // -------- END Форма "Регистрация" ----------

    // метод, кликающий по "Войти"
    @Step("Кликает по кнопке 'Войти'")
    public RegistrationPage clickLoginButton() {
        buttonLogin.click();
        return this;
    }

    // метод, кликающий по "Зарегистрироваться"
    @Step("Кликает по кнопке 'Зарегистрироваться'")
    public RegistrationPage clickRegistrationButton() {
        buttonRegistration.click();
        return this;
    }

    // метод для заполнения элемента формы нужным значением
    private void fillInTheField(SelenideElement formElement, String value) {
        formElement.sendKeys(value);
    }

    // метод "заполняющий" форму регистрации
    @Step("Заполняет поля формы регистрации")
    public RegistrationPage successfulFillRegistrationForm(String name, String email, String password) {
        fillInTheField(inputName, name);
        fillInTheField(inputEmail, email);
        fillInTheField(inputPassword, password);
        return this;
    }
}
