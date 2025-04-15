package ru.hazov.booksdemo.service.book_service.methods.getAllBooks;


import ru.hazov.booksdemo.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class GetAllBooksUtils {

    public static List<Book> allBooks() {
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
