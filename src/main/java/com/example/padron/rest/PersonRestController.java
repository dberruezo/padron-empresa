package com.example.padron.rest;

import com.example.padron.models.Person;
import com.example.padron.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personnel")
public class PersonRestController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> getPerson (
        @PathVariable("id") Integer id
    ) {
        Person person = personService.readPerson(id);

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson (
        @RequestBody Person person
    ) {
        Person createdPerson = personService.createPerson(person);

        if (createdPerson == null) {
            return ResponseEntity.notFound().build();
        }

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdPerson.getId())
            .toUri();

        return ResponseEntity
            .created(uri)
            .body(createdPerson);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Person> updatePerson (
        @RequestBody Person person,
        @PathVariable("id") Integer id
    ) {
        Person updatedPerson = personService.updatePerson(id, person);

        if (updatedPerson == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePerson (
        @PathVariable("id") Integer id
    ) {
        personService.deletePerson(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Person>> getActivePersonnel (
        @RequestParam(value = "cuit", required = false) Long cuit
    ) {
        List<Person> result = null;

        if (cuit != null) {
            result = personService.getPersonByCuit(cuit);
        } else {
            result = personService.getActivePersonnel();
        }

        return ResponseEntity.ok(result);
    }
}
