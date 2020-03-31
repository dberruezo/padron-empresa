package com.example.padron.rest;

import com.example.padron.models.Person;
import com.example.padron.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonRestController {
    @Autowired
    private IPersonRepository personRepository;

    @GetMapping(value = "/{id}")
    public Person getPerson (
        @PathVariable("id") Integer id
    ) {
        Optional<Person> query = personRepository.findById(id);

        if (query.isPresent()) {
            return query.get();
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Person with id " + id.toString() + " not found."
        );
    }

    @PutMapping
    public void saveOrUpdatePerson (
        @RequestBody Person person
    ) {
        personRepository.save(person);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson (
        @PathVariable("id") Integer id
    ) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Person with id " + id.toString() + " not found thus cannot be deleted."
        );
    }
}
