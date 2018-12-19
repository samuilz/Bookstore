package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.Puzzle;
import com.zahariev.bookstore.repositories.PuzzleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuzzleServiceImpl implements PuzzleService {
    private final PuzzleRepository puzzleRepository;

    @Autowired
    public PuzzleServiceImpl(PuzzleRepository puzzleRepository) {
        this.puzzleRepository = puzzleRepository;
    }

    @Override
    public List<Puzzle> getAll() {
        return puzzleRepository.getAll();
    }

    @Override
    public Puzzle getById(Integer id) {
        return puzzleRepository.getById(id);
    }

    @Override
    public List<Puzzle> getAllByNumberOfPiecesAndPartOfTitle(String pieces, String partOfTitle) {
        return puzzleRepository.getAllByNumberOfPiecesAndPartOfTitle(pieces, partOfTitle);
    }

    @Override
    public Puzzle update(Puzzle puzzle) {
        return puzzleRepository.update(puzzle);
    }
}
