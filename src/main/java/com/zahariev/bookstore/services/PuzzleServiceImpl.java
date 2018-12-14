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
    public List<Puzzle> getAllByNumberOfPiecesAndPartOfTitle(Integer numberOfPieces, String partOfTitle) {
        return puzzleRepository.getAllByNumberOfPiecesAndPartOfTitle(numberOfPieces, partOfTitle);
    }

    @Override
    public Puzzle purchasePuzzle(Puzzle puzzle) {
        return puzzleRepository.purchasePuzzle(puzzle);
    }
}
