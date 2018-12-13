package com.zahariev.bookstore.models;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends Product {
    private String authorsName;

//    @Column(name = "image")
//    private byte[] image;

    public Book() {

    }

    public Book(String name, Integer stock, Double price, String authorsName) {
        super(name, stock, price);
        this.authorsName = authorsName;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }
}
