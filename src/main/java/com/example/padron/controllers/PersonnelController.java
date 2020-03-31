package com.example.padron.controllers;

import com.example.padron.models.Person;
import com.example.padron.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/personnel")
public class PersonnelController {
    @Autowired
    private IPersonRepository personRepository;

    @RequestMapping("/active")
    public ModelAndView getActivePersonnel () {
        ModelAndView modelAndView = new ModelAndView("personnel");
        modelAndView.addObject("title", "Padrón empresa (ejemplo)");
        modelAndView.addObject(
            "personnel",
            personRepository.findByActiveTrue()
        );

        return modelAndView;
    }

    @RequestMapping("/query")
    public ModelAndView queryPersonnel (
        @ModelAttribute("person") Person person
    ) {
        ModelAndView modelAndView = new ModelAndView("query");
        modelAndView.addObject("title", "Padrón empresa (ejemplo)");

        if (person != null) {
            modelAndView.addObject("person", personRepository.findByCuit(person.getCuit()));
        }

        return modelAndView;
    }

    @RequestMapping(value = "/query/result", method = RequestMethod.POST)
    public ModelAndView queryPersonnel2 (
        @ModelAttribute("person") Person person
    ) {
        ModelAndView modelAndView = new ModelAndView("query");
        modelAndView.addObject("title", "Padrón empresa (ejemplo)");
        modelAndView.addObject("person", personRepository.findByCuit(person.getCuit()));

        return modelAndView;
    }
}
