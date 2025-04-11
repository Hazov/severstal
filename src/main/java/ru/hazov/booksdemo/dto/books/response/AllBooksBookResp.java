package ru.hazov.booksdemo.dto.books.response;

import ru.hazov.booksdemo.entity.Book;

public class AllBooksBookResp {
    private Long id;
    private String title;
    private String author;

    public static AllBooksBookResp fromBook(Book book) {
        AllBooksBookResp resp = new AllBooksBookResp();
        resp.setId(book.getId());
        resp.setTitle(book.getTitle());
        resp.setAuthor(book.getAuthor());
        return resp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
