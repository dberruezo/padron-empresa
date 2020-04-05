package com.example.padron.rest;

import com.example.padron.models.Phone;
import com.example.padron.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/phone")
public class PhoneRestController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Phone> getPhone (
        @PathVariable("id") Integer id
    ) {
        Phone phone = phoneService.readPhone(id);

        if (phone == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(phone);
    }

    @PostMapping
    public ResponseEntity<Phone> savePhone (
        @RequestBody Phone phone
    ) {
        Phone createdPhone = phoneService.createPhone(phone);

        if (createdPhone == null) {
            return ResponseEntity.notFound().build();
        }

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdPhone.getId())
            .toUri();

        return ResponseEntity
            .created(uri)
            .body(createdPhone);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePhone (
        @PathVariable("id") Integer id
    ) {
        phoneService.deletePhone(id);

        return ResponseEntity.noContent().build();
    }
}
