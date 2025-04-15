package ru.hazov.booksdemo.controller.book_controller.methods.searchBooks;

import ru.hazov.booksdemo.dto.books.search_books.request.BookFilterRequest;
import ru.hazov.booksdemo.entity.Book;

import java.util.List;

class SearchBookTestUtils {

    public static BookFilterRequest validBookFilterRequest() {
        BookFilterRequest bookFilterRequest = new BookFilterRequest();
        bookFilterRequest.setTitle("Book Title");
        return bookFilterRequest;
    }

    public static List<Book> persistedBooks() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthor("Author");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book Title2");
        book2.setAuthor("Author2");

        return List.of(book, book2);
    }
}
