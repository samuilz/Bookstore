package com.zahariev.bookstore.models;

import javax.persistence.*;

@Entity
@Table(name = "puzzles")
public class Puzzle extends Product {
    private Integer pieces;

//    private byte[] image;

    public Puzzle() {

    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }
}
