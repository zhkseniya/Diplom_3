package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Configuration;
import org.junit.Before;

public class BaseTest {
    @Before
    public void setUp() {
        Configuration config = new Configuration();
        config.pageLoadTimeout = 60000;
        config.startMaximized = true;
    }
}
