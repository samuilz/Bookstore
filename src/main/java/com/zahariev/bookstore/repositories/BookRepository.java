package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    Book getById(Integer id);

    List<Book> getAllByPartOfTitle(String partOfTitle);

    List<Book> getAllByAuthorsName(String authorsName);

    Book update(Book book);
}
