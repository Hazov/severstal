package ru.hazov.booksdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hazov.booksdemo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
