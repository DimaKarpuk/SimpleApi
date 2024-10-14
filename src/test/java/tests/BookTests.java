package tests;

import TestData.GetRandomBook;
import TestData.RandomUtils;
import apiSteps.ApiSteps;
import apiSteps.BookApiSteps;
import model.bookModel.GetBookModel;
import model.userModel.GetUserTokenModel;
import model.userModel.ResponseRegistrationModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static spec.BookStoreSpecs.requestSpecificationWithContentTypeJson;
import static spec.BookStoreSpecs.responseSpecificationWithStatusCode200;

public class BookTests extends TestBase {
    BookApiSteps bookApiSteps = new BookApiSteps();
    ApiSteps apiSteps = new ApiSteps();
    GetRandomBook getRandomBook = new GetRandomBook();
    RandomUtils randomUtils = new RandomUtils();
    @Test
    @DisplayName("Тест на получение списка книг")
    public void getBooksTest(){
        given(requestSpecificationWithContentTypeJson)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpecificationWithStatusCode200);
    }
    @Test()
    @DisplayName("Тест на получение рандомной книги")
    public void getRandomBookTest(){
        String book = getRandomBook.book();
       GetBookModel getBookModel = bookApiSteps.getBook(book);
       assertThat(getBookModel.getIsbn()).isEqualTo(book);
    }
    @Test()
    @DisplayName("Тест на добовление книги в корзину пользователя")
    public void addToCartBookTest(){
       ResponseRegistrationModel respRegModel = apiSteps.registrationNewUser(randomUtils.password, randomUtils.userName);
        GetUserTokenModel getToken = apiSteps.getUserToken(randomUtils.password, randomUtils.userName);
        bookApiSteps.addBookToCartUser(respRegModel.getUserID(),getRandomBook.book(),getToken.getToken());
        apiSteps.deleteUser(respRegModel.getUserID(),getToken.getToken());

    }
}
