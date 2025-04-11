package ru.hazov.booksdemo.dto.books.search_books.response;

import java.util.List;

public class SearchBooksResponse {
    private List<SearchBookResp> books;

    public List<SearchBookResp> getBooks() {
        return books;
    }

    public void setBooks(List<SearchBookResp> books) {
        this.books = books;
    }
}
