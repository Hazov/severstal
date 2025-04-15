package ru.hazov.booksdemo.service.book_service.methods.returnRentalBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.entity.Person;
import ru.hazov.booksdemo.exception.entity_exceptions.book.BookNotFoundException;
import ru.hazov.booksdemo.exception.entity_exceptions.person.PersonNotFoundException;
import ru.hazov.booksdemo.repository.PersonRepository;
import ru.hazov.booksdemo.service.BookService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceReturnRentalBookMethodTests {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    @DisplayName("returnRentalBook method test #1")
    public void givenPersonAndBook_whenReturnRentalBook_thenReturnPersistedBook() {
        //given
        Book persistedBook = ReturnRentalBookUtils.persistedBook();
        Person persistedPerson = ReturnRentalBookUtils.persistedPerson();
        persistedPerson.getBooks().add(persistedBook);
        assertFalse(persistedPerson.getBooks().isEmpty());
        //when
        Book book = bookService.returnRentalBook(persistedPerson, persistedBook);
        //then
        assertNotNull(book);
        assertTrue(persistedPerson.getBooks().isEmpty());
        verify(personRepository, never()).save(persistedPerson);
    }

    @Test
    @DisplayName("returnRentalBook method test #2")
    public void givenNotExistPersonAndBook_whenReturnRentalBook_thenThrowPersonNotFoundException() {
        //given
        Book persistedBook = ReturnRentalBookUtils.persistedBook();
        Person newPerson = ReturnRentalBookUtils.newPerson();
        //when //then
        assertThrows(PersonNotFoundException.class,
                () -> bookService.returnRentalBook(newPerson, persistedBook));
        verify(personRepository, never()).save(newPerson);
    }

    @Test
    @DisplayName("returnRentalBook method test #3")
    public void givenPersonAndNotExistBook_whenReturnRentalBook_thenThrowBookNotFoundException() {
        //given
        Book newBook = ReturnRentalBookUtils.newBook();
        Person persistedPerson = ReturnRentalBookUtils.persistedPerson();
        //when //then
        assertThrows(BookNotFoundException.class,
                () -> bookService.returnRentalBook(persistedPerson, newBook));
        verify(personRepository, never()).save(persistedPerson);
    }
}
