package ru.hazov.booksdemo.service.book_service.methods.rentBookById;

import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.entity.Person;

public class RentBookByIdUtils {

    public static Person persistedPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john@doe.com");
        return person;
    }

    public static Person newPerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john@doe.com");
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
