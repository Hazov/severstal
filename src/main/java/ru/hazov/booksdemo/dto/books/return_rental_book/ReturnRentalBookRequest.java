package ru.hazov.booksdemo.dto.books.return_rental_book;

import jakarta.validation.constraints.Positive;

public class ReturnRentalBookRequest {

    @Positive
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
