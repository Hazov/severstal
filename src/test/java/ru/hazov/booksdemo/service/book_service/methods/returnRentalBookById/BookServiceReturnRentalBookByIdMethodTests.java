package ru.hazov.booksdemo.service.book_service.methods.returnRentalBookById;

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
import ru.hazov.booksdemo.repository.BookRepository;
import ru.hazov.booksdemo.repository.PersonRepository;
import ru.hazov.booksdemo.service.BookService;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceReturnRentalBookByIdMethodTests {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    @DisplayName("returnRentalBookById method test #1")
    public void givenPersonAndBookId_whenReturnRentalBookById_thenReturnBook() {
        //given
        Person persistedPerson = ReturnRentalBookByIdUtils.persistedPerson();
        given(bookRepository.findById(anyLong()))
                .willReturn(Optional.of(ReturnRentalBookByIdUtils.persistedBook()));
        //when
        Book book = bookService.returnRentalBookById(persistedPerson, 1L);
        //then
        Assertions.assertNotNull(book);
        verify(personRepository, atMostOnce()).save(any(Person.class));
    }

    @Test
    @DisplayName("returnRentalBookById method test #2")
    public void givenPersonAndNotExistsBookId_whenReturnRentalBookById_thenReturnBook() {
        //given
        Person persistedPerson = ReturnRentalBookByIdUtils.persistedPerson();
        given(bookRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        //when //then
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.returnRentalBookById(persistedPerson, 1L));
        verify(personRepository, BDDMockito.never()).save(any(Person.class));
    }
}
