package hexlet.code.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Book {
    private int id;
    private String title;
    private String author;
    private Date publishedDate;
    private String isbn;
}
