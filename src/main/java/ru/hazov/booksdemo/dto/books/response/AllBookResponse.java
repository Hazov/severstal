package ru.hazov.booksdemo.dto.books.response;

import java.util.List;

public class AllBookResponse {

    List<AllBooksBookResp> books;

    public List<AllBooksBookResp> getBooks() {
        return books;
    }

    public void setBooks(List<AllBooksBookResp> books) {
        this.books = books;
    }
}
