package com.zahariev.bookstore.controllers;

import com.zahariev.bookstore.models.*;
import com.zahariev.bookstore.services.BoardGameService;
import com.zahariev.bookstore.services.OrderService;
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
@RequestMapping("/boardgames")
public class BoardGameController {
    private final BoardGameService boardGameService;
    private final UserService userService;
    private final OrderService orderService;
    private final RequestService requestService;

    @Autowired
    public BoardGameController(BoardGameService boardGameService, UserService userService, OrderService orderService, RequestService requestService) {
        this.boardGameService = boardGameService;
        this.userService = userService;
        this.orderService = orderService;
        this.requestService = requestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        List<BoardGame> boardGames = boardGameService.getAll();

        return new ModelAndView("boardgames.html", "boardGames", boardGames);
    }

    @RequestMapping(value = "/name/{partOfName}", method = RequestMethod.GET)
    public ModelAndView getAllByPartOfName(@PathVariable String partOfName) {
        List<BoardGame> boardGames = boardGameService.getAllByPartOfName(partOfName);

        return new ModelAndView("boardgames.html", "boardGames", boardGames);
    }

    @RequestMapping(value = "/players/{numberOfPlayers}", method = RequestMethod.GET)
    public ModelAndView getAllByNumberOfPlayers(@PathVariable String numberOfPlayers) {
        List<BoardGame> boardGames = boardGameService.getAllByNumberOfPlayers(Integer.parseInt(numberOfPlayers));

        return new ModelAndView("boardgames.html", "boardGames", boardGames);
    }

    @RequestMapping(value = "/purchase/{id}", method = RequestMethod.GET)
    public ModelAndView purchase(@PathVariable Integer id) {
        Product product = boardGameService.getById(id);

        return new ModelAndView("purchase.html", "product", product);
    }

    @RequestMapping(value = "/purchase/game/{id}", method = RequestMethod.POST)
    public ModelAndView purchaseBook(@PathVariable Integer id, User user) {
        BoardGame boardGame = boardGameService.getById(id);
        boardGameService.update(boardGame);

        User selectedUser = userService.save(user);

        Order order = new Order(boardGame, selectedUser);
        orderService.save(order);

        return new ModelAndView("boardgames.html", "boardGames", boardGameService.getAll());
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ModelAndView order(@PathVariable Integer id) {
        Product product = boardGameService.getById(id);

        return new ModelAndView("purchase.html", "product", product);
    }

    @RequestMapping(value = "/order/game/{id}", method = RequestMethod.POST)
    public ModelAndView orderBook(@PathVariable Integer id, User user) {
        BoardGame boardGame = boardGameService.getById(id);

        User selectedUser = userService.save(user);
        Request request = new Request(boardGame, selectedUser, RequestType.EXISTENT);
        requestService.save(request);
        return new ModelAndView("boardgames.html", "boardGames", boardGameService.getAll());
    }
}
