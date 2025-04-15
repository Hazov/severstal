package ru.hazov.booksdemo.service.book_service.methods.searchBooks;

import ru.hazov.booksdemo.dto.books.search_books.request.BookFilterRequest;
import ru.hazov.booksdemo.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class SearchBooksUtils {

    public static List<Book> books() {
        List<Book> books = new ArrayList<>();

        Book validTitleBook = new Book();
        validTitleBook.setTitle("Valid title");

        Book anotherTitleBook = new Book();
        anotherTitleBook.setTitle("Another");

        Book noTitleBook = new Book();
        books.add(validTitleBook);
        books.add(anotherTitleBook);
        books.add(noTitleBook);
        return books;

    }

    public static BookFilterRequest bookFilterRequestWithValidTitle() {
        BookFilterRequest bookFilterRequest = new BookFilterRequest();
        bookFilterRequest.setTitle("Valid title");
        return bookFilterRequest;
    }

    public static BookFilterRequest bookFilterRequestEmptyTitle() {
        BookFilterRequest bookFilterRequest = new BookFilterRequest();
        bookFilterRequest.setTitle("");
        return bookFilterRequest;
    }

    public static BookFilterRequest bookFilterRequestNullTitle() {
        return new BookFilterRequest();
    }
}
