package site.nomoreparties.stellarburgers.PageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {
    //url станицы
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement buttonLogin;

    // метод, кликающий по "Войти"
    @Step("Клик по кнопке 'Войти'")
    public ForgotPasswordPage clickLoginButton() {
        buttonLogin.click();
        return this;
    }

}
