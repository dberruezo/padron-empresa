package com.example.padron.rest;

import com.example.padron.models.Phone;
import com.example.padron.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/phone")
public class PhoneRestController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping(value = "/{id}")
    public Phone getPhone (
        @PathVariable("id") Integer id
    ) {
        Phone phone = phoneService.readPhone(id);

        return phone;

        /*
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Phone with id " + id.toString() + " not found."
        );
         */
    }

    @PostMapping
    public void savePhone (
        @RequestBody Phone phone
    ) {
        phoneService.createPhone(phone);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePhone (
        @PathVariable("id") Integer id
    ) {
        phoneService.deletePhone(id);

        /*
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Phone with id " + id.toString() + " not found thus cannot be deleted."
        );
         */
    }
}
