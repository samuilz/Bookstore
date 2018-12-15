package com.zahariev.bookstore.models;

import javax.persistence.*;

@Entity
@Table(name = "puzzles")
public class Puzzle extends Product {
    private Integer pieces;

//    private byte[] image;

    public Puzzle() {

    }

    public Puzzle(Integer id,String name, Integer stock, Double price, Integer pieces) {
        super(id, name, stock, price);
        this.pieces = pieces;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }
}
