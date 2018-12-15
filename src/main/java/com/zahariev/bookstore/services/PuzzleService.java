package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.Puzzle;

import java.util.List;

public interface PuzzleService {
    List<Puzzle> getAll();

    List<Puzzle> getAllByNumberOfPiecesAndPartOfTitle(String pieces, String partOfTitle);

    Puzzle purchasePuzzle(Puzzle puzzle);
}