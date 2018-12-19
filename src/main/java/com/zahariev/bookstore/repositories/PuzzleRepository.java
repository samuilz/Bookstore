package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.Book;
import com.zahariev.bookstore.models.Puzzle;

import java.util.List;

public interface PuzzleRepository {
    List<Puzzle> getAll();

    Puzzle getById(Integer id);

    List<Puzzle> getAllByNumberOfPiecesAndPartOfTitle(String pieces, String partOfTitle);

    Puzzle update(Puzzle puzzle);
}
