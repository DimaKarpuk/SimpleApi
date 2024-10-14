package apiSteps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.openqa.selenium.Cookie;
import io.restassured.parsing.Parser;
import model.userModel.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static spec.BookStoreSpecs.*;

public class ApiSteps {
    RequestRegistrationModel registrationModel = new RequestRegistrationModel();

    @Step("Регистрация нового пользователя")
    public ResponseRegistrationModel registrationNewUser(String password, String userName) {
        RestAssured.defaultParser = Parser.JSON;
        registrationModel.setPassword(password);
        registrationModel.setUserName(userName);
        return (given(requestSpecificationWithContentTypeJson)
                .body(registrationModel)
                .when()
                .post("Account/v1/User")
                .then()
                .spec(responseSpecificationWithStatusCode201)
                .extract().as(ResponseRegistrationModel.class));
    }

    @Step("Авторизация пользователя")
    public ResponseLoginUserModel loginUser(String password, String userName) {
        RestAssured.defaultParser = Parser.JSON;
        LoginRequestModel logModel = new LoginRequestModel();
        logModel.setPassword(password);
        logModel.setUserName(userName);
        return (given(requestSpecificationWithContentTypeJson)
                .body(registrationModel)
                .when()
                .post("Account/v1/Login")
                .then()
                .spec(responseSpecificationWithStatusCode200)
                .extract().as(ResponseLoginUserModel.class));
    }

    @Step("Удаление пользователя")
    public void deleteUser(String uId, String token) {
        given(requestWithLoginSpecification(token))
                .log().all()
                .when()
                .delete("/Account/v1/User/" + uId)
                .then()
                .log().all();
    }

    @Step("Получение данных пользователя")
    public GetUserTokenModel getUserToken(String password, String userName) {
        registrationModel.setPassword(password);
        registrationModel.setUserName(userName);
        return (given(requestSpecificationWithContentTypeJson)
                .body(registrationModel)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .spec(responseSpecificationWithStatusCode200)
                .extract().as(GetUserTokenModel.class));
    }
}
