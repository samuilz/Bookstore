package com.zahariev.bookstore.models;

public class BookClient {
    private Book book;
    private User user;

    public BookClient() {

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
