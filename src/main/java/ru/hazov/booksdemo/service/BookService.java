package ru.hazov.booksdemo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.hazov.booksdemo.dto.books.create_book.CreateBookRequest;
import ru.hazov.booksdemo.dto.books.search_books.request.BookFilterRequest;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.entity.Person;
import ru.hazov.booksdemo.exception.entity_exceptions.BookNotFoundException;
import ru.hazov.booksdemo.repository.BookRepository;
import ru.hazov.booksdemo.repository.PersonRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }
    @Async
    public CompletableFuture<List<Book>> getAllBook(){
        return CompletableFuture.completedFuture(bookRepository.findAll());
    }

    public List<Book> searchBooks(BookFilterRequest request) {
        List<Book> all = bookRepository.findAll();
        return all.stream()
                .filter(book -> book.getTitle().equals(request.getTitle()))
                .toList();
    }

    public Book createNewBook(CreateBookRequest request) {
        Book newBook = new Book();
        newBook.setTitle(request.getTitle());
        newBook.setAuthor(request.getAuthor());
        return bookRepository.save(newBook);
    }

    public Book rentBookById(Person person, Long bookId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(BookNotFoundException::new);
        return rentBook(person, book);
    }

    public Book rentBook(Person person, Book book) {
        person.getBooks().add(book);
        personRepository.save(person);
        return book;
    }

    public Book returnRentalBookById(Person person, Long bookId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(BookNotFoundException::new);
        return returnRentalBook(person, book);
    }

    public Book returnRentalBook(Person person, Book book) {
        person.getBooks().remove(book);
        personRepository.save(person);
        return book;
    }
}
