package ru.hazov.booksdemo.service.book_service.methods.getAllBooks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.repository.BookRepository;
import ru.hazov.booksdemo.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BookServiceGetAllBooksTestsMethodTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("getAllBooks method test #1")
    public void whenGetAllBooks_thenReturnAllBooks() {
        //given
        List<Book> allBooks = GetAllBooksUtils.allBooks();
        given(bookRepository.findAll()).willReturn(allBooks);
        //when
        CompletableFuture<List<Book>> dbBooks = bookService.getAllBooks();
        //then
        assertEquals(dbBooks.join().size(), allBooks.size());
    }

    @Test
    @DisplayName("getAllBooks method test #2")
    public void whenGetAllBooks_thenReturnEmptyList() {
        //given
        given(bookRepository.findAll()).willReturn(new ArrayList<>());
        //when
        CompletableFuture<List<Book>> dbBooks = bookService.getAllBooks();
        //then
        assertTrue(dbBooks.join().isEmpty());
    }
}
