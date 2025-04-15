package ru.hazov.booksdemo.repository.book_repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.hazov.booksdemo.entity.Book;
import ru.hazov.booksdemo.repository.BookRepository;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositorySecurityTestsTests {
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("findByAuthorAndTitle method test #1")
    void givenAuthorAndTitle_whenFindByAuthorAndTitle_thenReturnBookList(){

        //given
        String author = BookRepositoryUtils.existsAuthorInSet1();
        String title = BookRepositoryUtils.existsTitleInSet1();
        BookRepositoryUtils.persistBookSet1(bookRepository);
        //when
        Optional<Book> byAuthorAndTitleBooks = bookRepository.findByAuthorAndTitle(author, title);
        //then
        Assertions.assertTrue(byAuthorAndTitleBooks.isPresent());
        Assertions.assertEquals(byAuthorAndTitleBooks.get().getAuthor(), author);
        Assertions.assertEquals(byAuthorAndTitleBooks.get().getTitle(), title);

    }

    @Test
    @DisplayName("findByAuthorAndTitle method test #2")
    void givenNotExistsAuthorAndTitle_whenFindByAuthorAndTitle_thenReturnBookList(){
        //given
        String notExistsAuthor = BookRepositoryUtils.notExistsAuthorInSet1();
        String title = BookRepositoryUtils.existsTitleInSet1();
        BookRepositoryUtils.persistBookSet1(bookRepository);
        //when
        Optional<Book> byAuthorAndTitleBooks = bookRepository.findByAuthorAndTitle(notExistsAuthor, title);
        //then
        Assertions.assertFalse(byAuthorAndTitleBooks.isPresent());

    }

    @Test
    @DisplayName("findByAuthorAndTitle method test #3")
    void givenAuthorAndNotExistsTitle_whenFindByAuthorAndTitle_thenReturnBookList(){
        //given
        String author = BookRepositoryUtils.existsAuthorInSet1();
        String notExistsTitle = BookRepositoryUtils.notExistsTitleInSet1();
        BookRepositoryUtils.persistBookSet1(bookRepository);
        //when
        Optional<Book> byAuthorAndTitleBooks = bookRepository.findByAuthorAndTitle(author, notExistsTitle);
        //then
        Assertions.assertFalse(byAuthorAndTitleBooks.isPresent());
    }

}
