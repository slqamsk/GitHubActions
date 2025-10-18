import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.qameta.allure.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WikipediaTest {

    @BeforeAll
    static void beforeAll() {
        // Настройки Selenide
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

        // Для CI - будет переопределено через системные свойства
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "false"));
        Configuration.remote = System.getProperty("selenide.remote");

        // Allure listener
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Description("Тест01 - testWikipediaHomePage")
    public void testWikipediaHomePage() {
        step("Шаг01 - Открыть википедию", () -> {
            open("https://www.wikipedia.org");
        });

        step("Шаг02 - Проверить текст на странице", () -> {
            $("body").shouldHave(text("Wikipedia"));
        });
    }
}