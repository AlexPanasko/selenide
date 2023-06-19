package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessfullyCompleted() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));
    }

    @Test
    public void enterLatinCityTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Kirov");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void enterEmptyCityTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void enterMissingFromTheListCityTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Пышма");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void enterLatinNameTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Ivanov Alexey");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void enterEmptyNameTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void enterNumbersNameTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("555");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void enterShortPhoneTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+7999555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void enterPhoneWithoutPlusTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void enterEmptyPhoneTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void unmarkedCheckboxTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid").should(Condition.appear);
    }

    @Test
    public void enterEmptyDateTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(Condition.exactText("Неверно введена дата"));
    }

    @Test
    public void enterDateEarlierThanThreeDaysFromCurrentDateTest() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Киров");
        String currentDate = generateDate(1, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void shouldBeSuccessfullyCompletedWithDropDownListAndCalendar() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Ки");
        $(".input__menu").find(withText("Киров")).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfMeeting = LocalDate.now().plusDays(7);
        if (currentDate.getMonthValue() != dateOfMeeting.getMonthValue()) {
            $(".calendar__arrow_direction_right[data-step='1']").click();
        }
        $$("td.calendar__day").find(Condition.exactText(String.valueOf(dateOfMeeting.getDayOfMonth()))).click();
        $("[data-test-id='name'] input").setValue("Иванов Алексей");
        $("[data-test-id='phone'] input").setValue("+79995555555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        String dateOfMeetingFormat = dateOfMeeting.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateOfMeetingFormat));
    }
}
