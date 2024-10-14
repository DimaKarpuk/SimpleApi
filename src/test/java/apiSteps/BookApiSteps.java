package apiSteps;

import io.qameta.allure.Step;
import model.bookModel.AddBookToUserCartRequestModel;
import model.bookModel.GetBookModel;
import java.util.List;

import static io.restassured.RestAssured.given;
import static spec.BookStoreSpecs.*;


public class BookApiSteps {
    @Step("Получение списка книг")
    public List<String> getListBooks(){
        return (given(requestSpecificationWithContentTypeJson)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpecificationWithStatusCode200)
                .extract().body().jsonPath().getList("books.isbn"));
    }
    @Step("Получение рандомной книги")
    public GetBookModel getBook (String isbn){
      return (given(requestSpecificationWithContentTypeJson.queryParam("ISBN", isbn))
                .when()
                .get("/BookStore/v1/Book")
                .then()
                .spec(responseSpecificationWithStatusCode200)
                .extract().as(GetBookModel.class));
    }
    @Step("Добавление книги в корзину пользователя")
    public void addBookToCartUser(String userId, String isbn, String token){
        AddBookToUserCartRequestModel addBook = new AddBookToUserCartRequestModel();
        addBook.setUserId(userId);
        addBook.setIsbn(isbn);
        given(requestWithLoginSpecification(token))
                .body(addBook)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpecificationWithStatusCode201);
    }
}
