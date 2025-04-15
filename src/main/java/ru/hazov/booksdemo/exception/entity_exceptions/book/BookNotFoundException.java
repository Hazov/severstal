package ru.hazov.booksdemo.exception.entity_exceptions.book;


import ru.hazov.booksdemo.exception.ControllerException;

public class BookNotFoundException extends ControllerException {
    public BookNotFoundException() {
        super("Такой книги не существует");
    }
}