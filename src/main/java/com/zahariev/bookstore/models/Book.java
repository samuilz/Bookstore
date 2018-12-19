package com.zahariev.bookstore.models;

import javax.persistence.*;

@Entity
@Table(name = "books")
@DiscriminatorValue("Book")
public class Book extends Product {
    private String authorsName;

//    @Column(name = "image")
//    private byte[] image;

    public Book() {

    }

    public Book(Integer id, String name, Integer stock, Double price, String authorsName) {
            super(id, name, stock, price);
        this.authorsName = authorsName;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }
}
