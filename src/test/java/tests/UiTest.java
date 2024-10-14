package tests;

import TestData.GetRandomBook;
import TestData.RandomUtils;
import apiSteps.ApiSteps;
import apiSteps.BookApiSteps;
import model.userModel.GetUserTokenModel;
import model.userModel.ResponseRegistrationModel;
import org.junit.jupiter.api.Test;
import pages.UserPage;

import static com.codeborne.selenide.Selenide.open;

public class UiTest extends TestBase {
    ApiSteps apiSteps = new ApiSteps();
    BookApiSteps bookApiSteps = new BookApiSteps();
    UserPage userPage = new UserPage();
    GetRandomBook getRandomBook = new GetRandomBook();
    RandomUtils randomUtils = new RandomUtils();

    @Test
    public void DeleteBookFromCartTest() {
        ResponseRegistrationModel respRegModel = apiSteps.registrationNewUser(randomUtils.password, randomUtils.userName);
        GetUserTokenModel getUserTokenModel = apiSteps.getUserToken(randomUtils.password, randomUtils.userName);
        String book = getRandomBook.book();
        bookApiSteps.addBookToCartUser(respRegModel.getUserID(), book, getUserTokenModel.getToken());
        userPage.addCookie(respRegModel.getUserID(),getUserTokenModel.getExpires(),getUserTokenModel.getToken());
        userPage.openProfilePage();
    }
}
