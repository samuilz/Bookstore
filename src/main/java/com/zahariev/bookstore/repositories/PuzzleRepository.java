package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.Puzzle;

import java.util.List;

public interface PuzzleRepository {
    List<Puzzle> getAll();

    List<Puzzle> getAllByNumberOfPiecesAndPartOfTitle(Integer numberOfPieces, String partOfTitle);

    Puzzle purchasePuzzle(Puzzle puzzle);
}
