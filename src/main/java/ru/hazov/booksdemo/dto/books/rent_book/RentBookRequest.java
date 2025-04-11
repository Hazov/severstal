package ru.hazov.booksdemo.dto.books.rent_book;

import jakarta.validation.constraints.Positive;

public class RentBookRequest {
    @Positive
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
