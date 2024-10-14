package tests;

import TestData.RandomUtils;
import apiSteps.ApiSteps;
import model.userModel.GetUserTokenModel;
import model.userModel.LoginRequestModel;
import model.userModel.LoginResponseModel;
import model.userModel.ResponseRegistrationModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;
import static spec.BookStoreSpecs.*;

public class UserTests extends TestBase {

    ApiSteps apiSteps = new ApiSteps();
    LoginRequestModel logModel = new LoginRequestModel();
    RandomUtils randomUtils = new RandomUtils();
    String pass = randomUtils.password;
    String user = randomUtils.userName;

    @Test
    public void deleteUserTest() {
        ResponseRegistrationModel regModel = apiSteps.registrationNewUser(pass, user);
        GetUserTokenModel getToken = apiSteps.getUserToken(pass, user);
        given(requestWithLoginSpecification(getToken.getToken()))
                .when()
                .delete("/Account/v1/User/" + regModel.getUserID())
                .then()
                .spec(responseSpecificationWithStatusCode204);
    }

    @Test
    public void loginUserTest() {
        apiSteps.registrationNewUser(pass, user);
        logModel.setUserName(user);
        logModel.setPassword(pass);
      LoginResponseModel loginResponseModel = given(requestSpecificationWithContentTypeJson)
                .body(logModel)
                .when()
                .post("Account/v1/Login")
                .then()
                .spec(responseSpecificationWithStatusCode200)
               .extract().as(LoginResponseModel.class);
      assertThat(loginResponseModel.getUsername()).isEqualTo(user);
      assertThat(loginResponseModel.getUserId()).isNotEmpty();

    }
}