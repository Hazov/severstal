package ru.hazov.booksdemo.service.book_service.methods.returnRentalBook;

import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.entity.Person;

public class ReturnRentalBookUtils {


    public static Book persistedBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book 1");
        book.setAuthor("Author 1");
        return book;
    }

    public static Person persistedPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Person 1");
        person.setLastName("Person 2");
        return person;
    }

    public static Book newBook() {
        Book book = new Book();
        book.setTitle("Book 1");
        book.setAuthor("Author 1");
        return book;
    }

    public static Person newPerson() {
        Person person = new Person();
        person.setFirstName("Person 1");
        person.setLastName("Person 2");
        return person;
    }
}
