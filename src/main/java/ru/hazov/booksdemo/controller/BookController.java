package ru.hazov.booksdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hazov.booksdemo.dto.all_book.response.AllBookResponse;
import ru.hazov.booksdemo.dto.all_book.response.AllBooksBookResp;
import ru.hazov.booksdemo.dto.create_book.CreateBookRequest;
import ru.hazov.booksdemo.dto.create_book.CreateBookResponse;
import ru.hazov.booksdemo.dto.rent_book.RentBookRequest;
import ru.hazov.booksdemo.dto.rent_book.RentBookResponse;
import ru.hazov.booksdemo.dto.return_rental_book.ReturnRentalBookRequest;
import ru.hazov.booksdemo.dto.search_books.request.BookFilterRequest;
import ru.hazov.booksdemo.dto.search_books.response.SearchBookResp;
import ru.hazov.booksdemo.dto.search_books.response.SearchBooksResponse;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.entity.Person;
import ru.hazov.booksdemo.service.BookService;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Book>> allBook = bookService.getAllBook();
        List<Book> books = allBook.get();
        AllBookResponse response = new AllBookResponse();
        response.setBooks(books.stream()
                .map(AllBooksBookResp::fromBook)
                .toList()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createNewBook(CreateBookRequest request) {
        Book newBook = bookService.createNewBook(request);
        CreateBookResponse response = CreateBookResponse.fromBook(newBook);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/rent")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> rentBook(Principal currentUser, RentBookRequest request) {
        Book book = bookService.rentBookById((Person) currentUser, request.getBookId());
        RentBookResponse response = new RentBookResponse();
        response.setBookId(request.getBookId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/return_rental")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> returnRentalBook(Principal currentUser, ReturnRentalBookRequest request) {
        Book book = bookService.returnRentalBookById((Person) currentUser, request.getBookId());
        RentBookResponse response = new RentBookResponse();
        response.setBookId(request.getBookId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(BookFilterRequest request) {
        List<Book> allBooks = bookService.searchBooks(request);
        SearchBooksResponse response = new SearchBooksResponse();
        response.setBooks(allBooks.stream().map(SearchBookResp::fromBook).toList());
        return ResponseEntity.ok(response);
    }
}
