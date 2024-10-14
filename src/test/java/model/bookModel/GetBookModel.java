package model.bookModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBookModel {
    String isbn;
    String title;
    String subTitle;
    String author;
    String publish_date;
    String publisher;
    Integer pages;
    String descriptions;
    String website;
}
