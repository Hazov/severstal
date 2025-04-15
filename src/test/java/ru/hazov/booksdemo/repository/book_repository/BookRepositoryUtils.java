package ru.hazov.booksdemo.repository.book_repository;

import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.repository.BookRepository;

import java.util.List;

public class BookRepositoryUtils {

    public static String existsAuthorInSet1() {
        return "Author";
    }

    public static String notExistsAuthorInSet1() {
        return "Author11111";
    }



    public static String existsTitleInSet1() {
        return "Title";
    }

    public static String notExistsTitleInSet1() {
        return "Title11111";
    }

    public static void persistBookSet1(BookRepository bookRepository) {
        Book book = new Book();
        book.setAuthor("Author");
        book.setTitle("Title");
        bookRepository.save(book);

        Book book2 = new Book();
        book2.setAuthor("Author2");
        book2.setTitle("Title2");
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setAuthor("Author3");
        book3.setTitle("Title3");
        bookRepository.save(book3);

        List<Book> books = List.of(book, book2, book3);
        bookRepository.saveAll(books);

    }
}
