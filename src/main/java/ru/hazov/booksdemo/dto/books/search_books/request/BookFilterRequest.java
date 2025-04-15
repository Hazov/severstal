package ru.hazov.booksdemo.dto.books.search_books.request;

import org.hibernate.validator.constraints.Length;

public class BookFilterRequest {

    @Length(max = 150)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
