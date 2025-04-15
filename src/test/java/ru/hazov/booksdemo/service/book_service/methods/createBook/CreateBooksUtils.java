package ru.hazov.booksdemo.service.book_service.methods.createBook;

import ru.hazov.booksdemo.dto.books.create_book.CreateBookRequest;
import ru.hazov.booksdemo.entity.Book;

public class CreateBooksUtils {
    public static CreateBookRequest newBookRequest() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Book 2");
        createBookRequest.setAuthor("Author 2");
        return createBookRequest;
    }

    public static CreateBookRequest alreadyExistBookRequest() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Book 2");
        createBookRequest.setAuthor("Author 2");
        return createBookRequest;
    }

    public static Book persistedBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book 1");
        book.setAuthor("Author 1");
        return book;
    }
}
