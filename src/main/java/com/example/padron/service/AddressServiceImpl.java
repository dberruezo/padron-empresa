package com.example.padron.service;

import com.example.padron.models.Address;
import com.example.padron.repositories.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address readAddress(int id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public void updateAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(int id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }
    }
}
