package com.zahariev.bookstore.controllers;

import com.zahariev.bookstore.models.Puzzle;
import com.zahariev.bookstore.models.Request;
import com.zahariev.bookstore.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        List<Request> requests = requestService.getAll();

        return new ModelAndView("requests.html" ,"requests", requests);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView getAll(@PathVariable Integer id) {
        Request request = requestService.getById(id);
        requestService.delete(request);

        return new ModelAndView("requests.html" ,"requests", requestService.getAll());
    }
}
