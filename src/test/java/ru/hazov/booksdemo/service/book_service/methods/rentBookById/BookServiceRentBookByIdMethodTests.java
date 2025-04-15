package ru.hazov.booksdemo.service.book_service.methods.rentBookById;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.entity.Person;
import ru.hazov.booksdemo.exception.entity_exceptions.book.BookNotFoundException;
import ru.hazov.booksdemo.repository.BookRepository;
import ru.hazov.booksdemo.repository.PersonRepository;
import ru.hazov.booksdemo.service.BookService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceRentBookByIdMethodTests {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    @DisplayName("rentBookById method test #1")
    public void givenPersonAndBookId_whenRentBookById_thenReturnPersistedBook() {
        //given
        Person persistedPerson = RentBookByIdUtils.persistedPerson();
        given(bookRepository.findById(anyLong()))
                .willReturn(Optional.of(RentBookByIdUtils.persistedBook()));
        //when
        Book book = bookService.rentBookById(persistedPerson, 1L);
        //then
        assertNotNull(book);
        verify(personRepository, atMostOnce()).save(any());
    }

    @Test
    @DisplayName("rentBookById method test #2")
    public void givenPersonAndNotExistBook_whenRentBookById_thenReturnBookNotFoundException() {
        //given
        Person person = RentBookByIdUtils.persistedPerson();
        //when 
        given(bookRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        // then
        assertThrows(BookNotFoundException.class, () -> bookService.rentBookById(person, 1L));
        verify(personRepository, never()).save(any());
    }

}
