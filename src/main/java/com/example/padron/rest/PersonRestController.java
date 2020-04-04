package com.example.padron.rest;

import com.example.padron.models.Person;
import com.example.padron.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/personnel")
public class PersonRestController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public Person getPerson (
        @PathVariable("id") Integer id
    ) {
        return personService.readPerson(id);

        /*
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Person with id " + id.toString() + " not found."
        );
         */
    }

    @PostMapping
    public void savePerson (
        @RequestBody Person person
    ) {
        personService.createPerson(person);
    }

    @PutMapping
    public void updatePerson (
        @RequestBody Person person
    ) {
        personService.updatePerson(person);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson (
        @PathVariable("id") Integer id
    ) {
        personService.deletePerson(id);

        /*
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Person with id " + id.toString() + " not found thus cannot be deleted."
        );
         */
    }

    @GetMapping
    public List<Person> getActivePersonnel (
        @RequestParam(value = "cuit", required = false) Long cuit
    ) {
        if (cuit != null) {
            return personService.getPersonByCuit(cuit);
        }

        return personService.getActivePersonnel();
    }
}
