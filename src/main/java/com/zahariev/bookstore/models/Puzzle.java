package com.zahariev.bookstore.models;

public class Puzzle {
    private Integer puzzleId;
    private String title;
    private Integer pieces;

    public Puzzle() {

    }

    public Puzzle(String title, Integer pieces) {
        this.title = title;
        this.pieces = pieces;
    }

    public Integer getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(Integer puzzleId) {
        this.puzzleId = puzzleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }
}
