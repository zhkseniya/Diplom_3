package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BaseTest {
    ChromeDriver driver;

    @Before
    public void setUp() {
        Configuration config = new Configuration();
        config.pageLoadTimeout = 60000;
        config.startMaximized = true;
    }

    public void setUpYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\yandexdriver.exe");
        driver = new ChromeDriver();
        setWebDriver(driver);
    }

    public void closeYandexDriver() {
        driver.quit();
    }
}
