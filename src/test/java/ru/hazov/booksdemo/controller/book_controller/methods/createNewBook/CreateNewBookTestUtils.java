package ru.hazov.booksdemo.controller.book_controller.methods.createNewBook;

import ru.hazov.booksdemo.dto.books.create_book.CreateBookRequest;
import ru.hazov.booksdemo.entity.Book;

class CreateNewBookTestUtils {

    public static CreateBookRequest validCreateBookRequest() {
        CreateBookRequest request = new CreateBookRequest();
        request.setTitle("Book Title");
        request.setAuthor("Author");
        return request;
    }

    public static Book createdBook(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthor("Author");
        return book;
    }
}
