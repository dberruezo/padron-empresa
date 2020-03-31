package com.example.padron.rest;

import com.example.padron.models.Phone;
import com.example.padron.repositories.IPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/phone")
public class PhoneRestController {
    @Autowired
    private IPhoneRepository phoneRepository;

    @GetMapping(value = "/{id}")
    public Phone getPhone (
        @PathVariable("id") Integer id
    ) {
        Optional<Phone> query = phoneRepository.findById(id);

        if (query.isPresent()) {
            return query.get();
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Phone with id " + id.toString() + " not found."
        );
    }

    @PostMapping
    public void saveOrUpdatePhone (
        @RequestBody Phone phone
    ) {
        phoneRepository.save(phone);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePhone (
        @PathVariable("id") Integer id
    ) {
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Phone with id " + id.toString() + " not found thus cannot be deleted."
        );
    }
}
