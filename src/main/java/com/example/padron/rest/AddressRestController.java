package com.example.padron.rest;

import com.example.padron.models.Address;
import com.example.padron.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/address")
public class AddressRestController {
    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> getAddress (
        @PathVariable("id") Integer id
    ) {
        Address address = addressService.readAddress(id);

        if (address == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Object> saveAddress (
        @RequestBody Address address
    ) {
        Address createdAddress = addressService.createAddress(address);

        if (createdAddress == null) {
            return ResponseEntity.notFound().build();
        }

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdAddress.getId())
            .toUri();

        return ResponseEntity
            .created(uri)
            .body(createdAddress);
    }

    @PutMapping
    public void updateAddress (
        @RequestBody Address address
    ) {
        addressService.updateAddress(address);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddress (
        @PathVariable("id") Integer id
    ) {
        addressService.deleteAddress(id);

        /*
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Address with id " + id.toString() + " not found thus cannot be deleted."
        );
         */
    }
}
