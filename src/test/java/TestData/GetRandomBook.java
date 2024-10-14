package TestData;

import apiSteps.BookApiSteps;

import java.util.List;

public class GetRandomBook {
    BookApiSteps bookApiSteps = new BookApiSteps();
    public String book() {
        List<String> getBooksModel = bookApiSteps.getListBooks();
        int random = (int) (Math.random() * getBooksModel.size());
        String book = String.valueOf(getBooksModel.get(random));
        return book;
    }
}
