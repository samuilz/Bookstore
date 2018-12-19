package com.zahariev.bookstore.controllers;

import com.zahariev.bookstore.models.*;
import com.zahariev.bookstore.services.OrderService;
import com.zahariev.bookstore.services.PuzzleService;
import com.zahariev.bookstore.services.RequestService;
import com.zahariev.bookstore.services.UserService;
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
    private final UserService userService;
    private final OrderService orderService;
    private final RequestService requestService;

    @Autowired
    public PuzzleController(PuzzleService puzzleService, UserService userService, OrderService orderService, RequestService requestService) {
        this.puzzleService = puzzleService;
        this.userService = userService;
        this.orderService = orderService;
        this.requestService = requestService;
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

    @RequestMapping(value = "/purchase/{id}", method = RequestMethod.GET)
    public ModelAndView purchase(@PathVariable Integer id) {
        Product product = puzzleService.getById(id);

        return new ModelAndView("purchase.html", "product", product);
    }

    @RequestMapping(value = "/purchase/puzzle/{id}", method = RequestMethod.POST)
    public ModelAndView purchaseBook(@PathVariable Integer id, User user) {
        Puzzle puzzle = puzzleService.getById(id);
        puzzleService.update(puzzle);

        User selectedUser = userService.save(user);

        Order order = new Order(puzzle, selectedUser);
        orderService.save(order);

        return new ModelAndView("puzzles.html", "puzzles", puzzleService.getAll());
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ModelAndView order(@PathVariable Integer id) {
        Product product = puzzleService.getById(id);

        return new ModelAndView("purchase.html", "product", product);
    }

    @RequestMapping(value = "/order/puzzle/{id}", method = RequestMethod.POST)
    public ModelAndView orderBook(@PathVariable Integer id, User user) {
        Puzzle puzzle = puzzleService.getById(id);

        User selectedUser = userService.save(user);
        Request request = new Request(puzzle, selectedUser, RequestType.EXISTENT);
        requestService.save(request);
        return new ModelAndView("puzzles.html", "puzzles", puzzleService.getAll());
    }
}
