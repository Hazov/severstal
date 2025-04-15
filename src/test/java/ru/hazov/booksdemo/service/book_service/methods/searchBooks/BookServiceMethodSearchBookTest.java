package ru.hazov.booksdemo.service.book_service.methods.searchBooks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hazov.booksdemo.dto.books.search_books.request.BookFilterRequest;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.repository.BookRepository;
import ru.hazov.booksdemo.service.BookService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BookServiceMethodSearchBookTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    @DisplayName("searchBooks method test #1")
    public void givenBookFilterRequest_whenSearchBooks_thenReturnBookList() {
        //given
        BookFilterRequest request = SearchBooksUtils.bookFilterRequestWithValidTitle();
        given(bookRepository.findAll()).willReturn(SearchBooksUtils.books());
        //when
        List<Book> books = bookService.searchBooks(request);
        //then
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertTrue(books.stream().noneMatch(book -> book.getTitle() == null));
    }

    @Test
    @DisplayName("searchBooks method test #2")
    public void givenBookFilterRequestWithEmptyTitle_whenSearchBooks_thenReturnBooks() {
        //given
        BookFilterRequest request = SearchBooksUtils.bookFilterRequestEmptyTitle();
        given(bookRepository.findAll()).willReturn(SearchBooksUtils.books());
        //when
        List<Book> books = bookService.searchBooks(request);
        //then
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(2, books.size());
        assertTrue(books.stream().noneMatch(book -> book.getTitle() == null));
    }

    @Test
    @DisplayName("searchBooks method test #3")
    public void givenBookFilterRequestWithNullTitle_whenSearchBooks_thenReturnAllBooks() {
        //given
        BookFilterRequest request = SearchBooksUtils.bookFilterRequestNullTitle();
        given(bookRepository.findAll()).willReturn(SearchBooksUtils.books());
        //when
        List<Book> books = bookService.searchBooks(request);
        //then
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(SearchBooksUtils.books().size(), books.size());
    }

}
