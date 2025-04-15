package ru.hazov.booksdemo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author extends Person {

    @OneToMany
    List<Book> ownBooks = new ArrayList<>();

    public List<Book> getOwnBooks() {
        return ownBooks;
    }

    public void setOwnBooks(List<Book> ownBooks) {
        this.ownBooks = ownBooks;
    }
}
