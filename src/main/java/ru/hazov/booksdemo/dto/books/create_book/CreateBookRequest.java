package ru.hazov.booksdemo.dto.books.create_book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CreateBookRequest {

    @NotEmpty
    @NotNull
    @Length(max = 150)
    private String title;

    @NotEmpty
    @NotNull
    @Length(max = 150)
    private String author;

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
