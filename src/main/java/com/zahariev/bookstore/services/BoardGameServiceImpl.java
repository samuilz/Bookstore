package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.BoardGame;
import com.zahariev.bookstore.repositories.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardGameServiceImpl implements BoardGameService {
    private final BoardGameRepository boardGameRepository;

    @Autowired
    public BoardGameServiceImpl(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }

    @Override
    public List<BoardGame> getAll() {
        return boardGameRepository.getAll();
    }

    @Override
    public List<BoardGame> getAllByPartOfName(String partOfName) {
        return boardGameRepository.getAllByPartOfName(partOfName);
    }

    @Override
    public List<BoardGame> getAllByNumberOfPlayers(Integer numberOfPlayers) {
        return boardGameRepository.getAllByNumberOfPlayers(numberOfPlayers);
    }

    @Override
    public BoardGame purchaseBoardGame(BoardGame boardGame) {
        return boardGameRepository.purchaseBoardGame(boardGame);
    }
}
