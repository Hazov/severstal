package ru.hazov.booksdemo.dto.create_book;

import ru.hazov.booksdemo.entity.Book;

public class CreateBookResponse {
    private  Long id;

    private String title;

    private String author;

    public static CreateBookResponse fromBook(Book newBook) {
        CreateBookResponse response = new CreateBookResponse();
        response.setId(newBook.getId());
        response.setTitle(newBook.getTitle());
        response.setAuthor(newBook.getAuthor());
        return response;
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
