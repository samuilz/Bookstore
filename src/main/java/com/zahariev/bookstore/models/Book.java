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
    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }
}
