package ru.hazov.booksdemo.controller.book_controller.methods.returnRentalBook;

import ru.hazov.booksdemo.dto.books.rent_book.RentBookRequest;
import ru.hazov.booksdemo.dto.books.return_rental_book.ReturnRentalBookRequest;
import ru.hazov.booksdemo.entity.Book;

class ReturnRentalBookTestUtils {

    public static ReturnRentalBookRequest validReturnRentalBookRequest() {
        ReturnRentalBookRequest rentBookRequest = new ReturnRentalBookRequest();
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
