package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UserPage {
    @Step("Добовляем cookie авторизации")
    public void addCookie(String userID, String expires, String token) {
        open("https://demoqa.com/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", userID));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        getWebDriver().manage().addCookie(new Cookie("token", token));
    }
    @Step("Открываем страницу профиль")
    public void openProfilePage() {
        open("https://demoqa.com/profile");
    }
}
