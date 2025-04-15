package ru.hazov.booksdemo.service.book_service.methods.rentBook;

import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.entity.Person;

public class RentBookUtils {

    public static Person persistedPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Renter");
        person.setLastName("Booker");
        return person;
    }

    public static Book persistedBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book");
        book.setAuthor("Author");
        return book;
    }

    public static Person newPerson() {
        Person person = new Person();
        person.setFirstName("newRenter");
        person.setLastName("newBooker");
        return person;
    }

    public static Book newBook() {
        Book book = new Book();
        book.setTitle("newBook");
        book.setAuthor("newAuthor");
        return book;
    }
}
