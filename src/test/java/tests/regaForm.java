package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class regaForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void FillTest() throws InterruptedException {
        //open url
        open("https://demoqa.com/automation-practice-form");

        //personal data
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("j.doe@mail.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        //subjects
        $("#subjectsInput").setValue("English").pressEnter();
        $(byText("Music")).click();
        $(byText("Sports")).click();
        $(byText("Reading")).click();

        //date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("2");
        $(".react-datepicker__year-select").selectOptionByValue("1996");
        $(".react-datepicker__day.react-datepicker__day--002").click();

        //profile pic
        $("#uploadPicture").uploadFromClasspath("1596044545241977310.jpg");

        //location
        $("#currentAddress").setValue("Russia, Saint Petersburg");
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        //verification
        $(".table").shouldHave(text("John Doe"),
                text("j.doe@mail.com"),
                text("Male"),
                text("1234567890"),
                text("2 April, 1996"),
                text("English"),
                text("Music, Sports, Reading"),
                text("1596044545241977310.jpg"),
                text("Russia, Saint Petersburg"),
                text("NCR Delhi"));
    }
}
