package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.Book;
import com.zahariev.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public List<Book> getAllByPartOfTitle(String partOfTitle) {
        return bookRepository.getAllByPartOfTitle(partOfTitle);
    }

    @Override
    public List<Book> getAllByAuthorsName(String authorsName) {
        return bookRepository.getAllByAuthorsName(authorsName);
    }

    @Override
    public Book purchaseBook(Book book) {
        return bookRepository.purchaseBook(book);
    }
}
