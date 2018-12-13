package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    List<Book> getAllByPartOfTitle(String partOfTitle);

    List<Book> getAllByAuthorsName(String authorsName);

    Book purchaseBook(Book book);
}
