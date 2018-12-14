package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.BoardGame;

import java.util.List;

public interface BoardGameService {
    List<BoardGame> getAll();

    List<BoardGame> getAllByPartOfName(String partOfName);

    List<BoardGame> getAllByNumberOfPlayers(Integer numberOfPlayers);

    BoardGame purchaseBoardGame(BoardGame boardGame);
}
