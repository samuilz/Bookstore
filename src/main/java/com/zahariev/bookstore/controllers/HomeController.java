package com.zahariev.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage() {
        return "index.html";
    }
}
