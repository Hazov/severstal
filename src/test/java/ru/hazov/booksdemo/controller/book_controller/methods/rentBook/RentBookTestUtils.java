package ru.hazov.booksdemo.controller.book_controller.methods.rentBook;

import ru.hazov.booksdemo.dto.books.rent_book.RentBookRequest;
import ru.hazov.booksdemo.entity.Book;

class RentBookTestUtils {

    public static RentBookRequest validRentBookRequest() {
        RentBookRequest rentBookRequest = new RentBookRequest();
        rentBookRequest.setBookId(1L);
        return rentBookRequest;
    }

    public static Book persistedBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthor("Author");
        return book;
    }
}
