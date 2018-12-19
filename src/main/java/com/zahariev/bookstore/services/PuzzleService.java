package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.Puzzle;

import java.util.List;

public interface PuzzleService {
    List<Puzzle> getAll();

    Puzzle getById(Integer id);

    List<Puzzle> getAllByNumberOfPiecesAndPartOfTitle(String pieces, String partOfTitle);

    Puzzle update(Puzzle puzzle);
}
