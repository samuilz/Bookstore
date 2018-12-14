package com.zahariev.bookstore.controllers;

import com.zahariev.bookstore.models.Book;
import com.zahariev.bookstore.services.BookService;
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

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
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
}
