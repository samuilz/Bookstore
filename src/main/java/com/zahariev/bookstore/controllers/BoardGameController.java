package com.zahariev.bookstore.controllers;

import com.zahariev.bookstore.models.BoardGame;
import com.zahariev.bookstore.services.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/boardgames")
public class BoardGameController {
    private final BoardGameService boardGameService;

    @Autowired
    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        List<BoardGame> boardGames = boardGameService.getAll();

        return new ModelAndView("boardgames.html" ,"boardGames", boardGames);
    }

    @RequestMapping(value = "/name/{partOfName}",method = RequestMethod.GET)
    public ModelAndView getAllByPartOfName(@PathVariable String partOfName) {
        List<BoardGame> boardGames = boardGameService.getAllByPartOfName(partOfName);

        return new ModelAndView("boardgames.html" ,"boardGames", boardGames);
    }

    @RequestMapping(value = "/players/{numberOfPlayers}",method = RequestMethod.GET)
    public ModelAndView getAllByNumberOfPlayers(@PathVariable String numberOfPlayers) {
        List<BoardGame> boardGames = boardGameService.getAllByNumberOfPlayers(Integer.parseInt(numberOfPlayers));

        return new ModelAndView("boardgames.html" ,"boardGames", boardGames);
    }
}
