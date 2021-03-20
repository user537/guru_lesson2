package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegaForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    String firstName = "John";
    String lastName = "Doe";
    String userEmail = "j.doe@mail.com";
    String userNumber = "1234567890";
    String subject = "English";
    String currentAddress = "Russia, Saint Petersburg";

    //only in verification
    String hobbie = "Music, Sports, Reading";
    String birthDate ="02 April,1996";

    @Test
    void FillTest() {
        //open url
        open("https://demoqa.com/automation-practice-form");

        //personal data
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText("Male")).click();
        $("#userNumber").setValue(userNumber);

        //subjects
        $("#subjectsInput").setValue(subject).pressEnter();
        $(byText("Music")).click();
        $(byText("Sports")).click();
        $(byText("Reading")).click();

        //date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__day--002").click();


        //profile pic
        $("#uploadPicture").uploadFromClasspath("1596044545241977310.jpg");

        //location
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        //verification
        $(".modal-content").shouldBe(visible);
        $(".modal-content").shouldHave(text(firstName + " " + lastName),
                text(userEmail),
                text("Male"),
                text(userNumber),
                text(birthDate),
                text(subject),
                text(hobbie),
                text("1596044545241977310.jpg"),
                text(currentAddress),
                text("NCR Delhi"));
    }
}
