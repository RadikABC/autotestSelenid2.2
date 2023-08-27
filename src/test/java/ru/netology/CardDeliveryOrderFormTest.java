package ru.netology;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderFormTest {
    @Test
    void shouldTest() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 14;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ульяновск");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue("10.09.2023");

        $("[data-test-id=name] input").setValue("Баранов Василий");
        $("[data-test-id=phone] input").setValue("+79270002277");
        $("[data-test-id=agreement]").click();
        $x("//div//span[text()='Забронировать']").click();
        $x("[data-test-id=notification]").shouldHave(exactText("Встреча успешно забронирована"));
    }
}

