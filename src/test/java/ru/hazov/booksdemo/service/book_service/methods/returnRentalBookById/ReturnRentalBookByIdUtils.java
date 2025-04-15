package ru.hazov.booksdemo.service.book_service.methods.returnRentalBookById;

import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.entity.Person;

public class ReturnRentalBookByIdUtils {
    public static Person persistedPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Doe");
        return person;
    }

    public static Book persistedBook() {
        Book book = new Book();
        book.setId(1L);
        book.setAuthor("John Doe");
        book.setTitle("Book Title");
        return book;
    }
}
