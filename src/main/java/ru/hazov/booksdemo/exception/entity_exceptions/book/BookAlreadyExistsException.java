package ru.hazov.booksdemo.exception.entity_exceptions.book;

import ru.hazov.booksdemo.exception.ControllerException;

public class BookAlreadyExistsException extends ControllerException {
    public BookAlreadyExistsException() {
        super("Такая книга уже существует");
    }
}
