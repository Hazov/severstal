package ru.hazov.booksdemo.exception.entity_exceptions;

import ru.hazov.booksdemo.exception.ControllerException;

public class PersonNotFoundException extends ControllerException {
    public PersonNotFoundException() {
        super("Такого пользователя не существует");
    }
}
