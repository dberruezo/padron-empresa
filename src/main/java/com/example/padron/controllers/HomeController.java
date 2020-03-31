package com.example.padron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView viewHomePage () {
        ModelAndView homeView = new ModelAndView("index");
        homeView.addObject("title", "Padrón empresa (ejemplo)");
        return homeView;
    }
}
