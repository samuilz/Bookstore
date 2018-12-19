package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.BoardGame;
import com.zahariev.bookstore.models.Book;

import java.util.List;

public interface BoardGameRepository {
    List<BoardGame> getAll();

    BoardGame getById(Integer id);

    List<BoardGame> getAllByPartOfName(String partOfName);

    List<BoardGame> getAllByNumberOfPlayers(Integer numberOfPlayers);

    BoardGame update(BoardGame boardGame);
}
