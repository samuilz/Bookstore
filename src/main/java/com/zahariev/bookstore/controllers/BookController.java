package com.zahariev.bookstore.controllers;

import com.zahariev.bookstore.models.*;
import com.zahariev.bookstore.services.BookService;
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
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final OrderService orderService;
    private final UserService userService;
    private final RequestService requestService;

    @Autowired
    public BookController(BookService bookService, OrderService orderService, UserService userService, RequestService requestService) {
        this.bookService = bookService;
        this.orderService = orderService;
        this.userService = userService;
        this.requestService = requestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        List<Book> books = bookService.getAll();

       return new ModelAndView("books.html" ,"books", books);
    }

    @RequestMapping(value = "/title/{partOfTitle}", method = RequestMethod.GET)
    public ModelAndView getAllByPartOfTitle(@PathVariable String partOfTitle) {
        List<Book> books = bookService.getAllByPartOfTitle(partOfTitle);

        return new ModelAndView("books.html" ,"books", books);
    }

    @RequestMapping(value = "/author/{authorsName}" ,method = RequestMethod.GET)
    public ModelAndView getAllByAuthorsName(@PathVariable String authorsName) {
        List<Book> books = bookService.getAllByAuthorsName(authorsName);

        return new ModelAndView("books.html" ,"books", books);
    }

    @RequestMapping(value = "/purchase/{id}", method = RequestMethod.GET)
    public ModelAndView purchase(@PathVariable Integer id) {
        Product product = bookService.getById(id);

        return new ModelAndView("purchase.html", "product", product);
    }

    @RequestMapping(value = "/purchase/book/{id}", method = RequestMethod.POST)
    public ModelAndView purchaseBook(@PathVariable Integer id, User user) {
        Book book = bookService.getById(id);
        bookService.update(book);

        User selectedUser = userService.save(user);

        Order order = new Order(book, selectedUser);
        orderService.save(order);

        return new ModelAndView("books.html", "books", bookService.getAll());
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ModelAndView order(@PathVariable Integer id) {
        Product product = bookService.getById(id);

        return new ModelAndView("purchase.html", "product", product);
    }

    @RequestMapping(value = "/order/book/{id}", method = RequestMethod.POST)
    public ModelAndView orderBook(@PathVariable Integer id, User user) {
        Book book = bookService.getById(id);

        User selectedUser = userService.save(user);

        Request request = new Request(book, selectedUser, RequestType.EXISTENT);
        requestService.save(request);
        return new ModelAndView("books.html", "books", bookService.getAll());
    }
}
