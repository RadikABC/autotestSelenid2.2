package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.awt.event.KeyEvent;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardDeliveryOrderFormTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldTest() {

        String planningDate = generateDate(4);


        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Ульяновск");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);

        $("[data-test-id=date] input").setValue(planningDate);

        $("[data-test-id=name] input").setValue("Баранов Василий");
        $("[data-test-id=phone] input").setValue("+79270002277");
        $("[data-test-id=agreement]").click();
        $x("//div//span[text()='Забронировать']").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));
    }
}

