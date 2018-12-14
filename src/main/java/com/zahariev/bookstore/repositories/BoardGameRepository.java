package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.BoardGame;

import java.util.List;

public interface BoardGameRepository {
    List<BoardGame> getAll();

    List<BoardGame> getAllByPartOfName(String partOfName);

    List<BoardGame> getAllByNumberOfPlayers(Integer numberOfPlayers);

    BoardGame purchaseBoardGame(BoardGame boardGame);
}
