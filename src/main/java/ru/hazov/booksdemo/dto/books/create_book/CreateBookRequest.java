package ru.hazov.booksdemo.dto.books.create_book;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class CreateBookRequest {

    @NotEmpty
    @Length(max = 150)
    public String title;

    @NotEmpty
    @Length(max = 150)
    public String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
