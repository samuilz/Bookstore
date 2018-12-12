package com.zahariev.bookstore.models;

public class Book {
    private Integer bookId;
    private String title;
    private String authorsName;

    public Book() {

    }

    public Book(String title, String authorsName) {
        this.title = title;
        this.authorsName = authorsName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }
}
