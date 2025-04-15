package ru.hazov.booksdemo.service.book_service.methods.createBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hazov.booksdemo.dto.books.create_book.CreateBookRequest;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.exception.entity_exceptions.book.BookAlreadyExistsException;
import ru.hazov.booksdemo.repository.BookRepository;
import ru.hazov.booksdemo.service.BookService;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceCreateBookMethodTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    @DisplayName("createBook method test #1")
    public void givenCreateBookRequest_whenCreateBook_thenReturnPersistedBook() {
        //given
        CreateBookRequest createBookRequest = CreateBooksUtils.newBookRequest();
        Book persistedBook = CreateBooksUtils.persistedBook();

        given(bookRepository.save(BDDMockito.any(Book.class)))
                .willReturn(CreateBooksUtils.persistedBook());
        //when
        Book newBook = bookService.createNewBook(createBookRequest);
        //then
        verify(bookRepository, BDDMockito.atMostOnce()).save(BDDMockito.any(Book.class));
        assertEquals(persistedBook.getTitle(), newBook.getTitle());
        assertEquals(persistedBook.getAuthor(), newBook.getAuthor());
        assertEquals(persistedBook.getId(), 1L);
    }

    @Test
    @DisplayName("createBook method test #2")
    public void givenCreateBookRequestWithEmptyTitle_whenCreateBook_thenThrowException() {
        //given
        CreateBookRequest createBookRequest = CreateBooksUtils.alreadyExistBookRequest();
        given(bookRepository.findByAuthorAndTitle(BDDMockito.anyString(), BDDMockito.anyString()))
                .willReturn(Optional.of(CreateBooksUtils.persistedBook()));
        //when //then
        assertThrows(BookAlreadyExistsException.class, () -> bookService.createNewBook(createBookRequest));
        verify(bookRepository, BDDMockito.never()).save(BDDMockito.any(Book.class));
    }

}
