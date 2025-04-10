package ru.hazov.booksdemo.dto.return_rental_book;

public class ReturnRentalBookRequest {
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
