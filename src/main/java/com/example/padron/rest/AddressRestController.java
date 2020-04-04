package com.example.padron.rest;

import com.example.padron.models.Address;
import com.example.padron.repositories.IAddressRepository;
import com.example.padron.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressRestController {
    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/{id}")
    public Address getAddress (
        @PathVariable("id") Integer id
    ) {
        return addressService.readAddress(id);

        /*
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Address with id " + id.toString() + " not found."
        );
         */
    }

    @PostMapping
    public void saveAddress (
        @RequestBody Address address
    ) {
        addressService.createAddress(address);
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
