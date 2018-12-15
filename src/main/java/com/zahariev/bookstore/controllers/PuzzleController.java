package com.zahariev.bookstore.controllers;

import com.zahariev.bookstore.models.BoardGame;
import com.zahariev.bookstore.models.Puzzle;
import com.zahariev.bookstore.services.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/puzzles")
public class PuzzleController {
    private final PuzzleService puzzleService;

    @Autowired
    public PuzzleController(PuzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        List<Puzzle> puzzles = puzzleService.getAll();

        return new ModelAndView("puzzles.html" ,"puzzles", puzzles);
    }

    @RequestMapping(value = "/{pieces}/{word}",method = RequestMethod.GET)
    public ModelAndView getAllByNumberOfPiecesAndWordFromTitle(@PathVariable String pieces, @PathVariable String word) {
        List<Puzzle> puzzles = puzzleService.getAllByNumberOfPiecesAndPartOfTitle(pieces, word);

        return new ModelAndView("puzzles.html" ,"puzzles", puzzles);
    }
}
