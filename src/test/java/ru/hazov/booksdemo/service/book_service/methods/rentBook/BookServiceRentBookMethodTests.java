package ru.hazov.booksdemo.service.book_service.methods.rentBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceRentBookMethodTests {


    @InjectMocks
    private BookService bookService;

    @Mock
    private PersonRepository personRepository;


    @Test
    @DisplayName("rentBook method test #1")
    public void givenCreateBookRequest_whenCreateBook_thenReturnPersistedBook() {
        //given
        Person persistedPerson = RentBookUtils.persistedPerson();
        Book persistedBook = RentBookUtils.persistedBook();
        Assertions.assertTrue(persistedPerson.getBooks().isEmpty());
        //when
        Book book = bookService.rentBook(persistedPerson, persistedBook);
        //then
        assertNotNull(book);
        verify(personRepository, BDDMockito.times(1)).save(persistedPerson);
        assertFalse(persistedPerson.getBooks().isEmpty());
    }

    @Test
    @DisplayName("rentBook method test #2")
    public void givenCreateBookRequestWithNotExistsPerson_whenCreateBook_thenThrowPersonNotFoundException() {
        //given
        Person newPerson = RentBookUtils.newPerson();
        Book newBook = RentBookUtils.persistedBook();
        //when //then
        assertThrows(PersonNotFoundException.class, () -> bookService.rentBook(newPerson, newBook));
        verify(personRepository, BDDMockito.times(0)).save(newPerson);
    }

    @Test
    @DisplayName("rentBook method test #3")
    public void givenCreateBookRequestWithNotExistsBook_whenCreateBook_thenThrowBookNotFoundException() {
        //given
        Person newPerson = RentBookUtils.persistedPerson();
        Book newBook = RentBookUtils.newBook();
        //when //then
        assertThrows(BookNotFoundException.class, () -> bookService.rentBook(newPerson, newBook));
        verify(personRepository, BDDMockito.never()).save(newPerson);
    }

}
