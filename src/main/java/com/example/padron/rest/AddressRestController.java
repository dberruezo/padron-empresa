package com.example.padron.rest;

import com.example.padron.models.Address;
import com.example.padron.repositories.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressRestController {
    @Autowired
    private IAddressRepository addressRepository;

    @GetMapping(value = "/{id}")
    public Address getAddress (
        @PathVariable("id") Integer id
    ) {
        Optional<Address> query = addressRepository.findById(id);

        if (query.isPresent()) {
            return query.get();
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Address with id " + id.toString() + " not found."
        );
    }

    @PutMapping
    public void saveOrUpdateAddress (
        @RequestBody Address address
    ) {
        addressRepository.save(address);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddress (
        @PathVariable("id") Integer id
    ) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Address with id " + id.toString() + " not found thus cannot be deleted."
        );
    }
}
