package com.example.padron.service;

import com.example.padron.models.Address;
import com.example.padron.repositories.IAddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        Address newAddress = null;

        try {
            newAddress = addressRepository.save(address);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return newAddress;
    }

    @Override
    public Address readAddress(int id) {
        return addressRepository
            .findById(id)
            .orElse(null);
    }

    @Override
    public Address updateAddress(int id, Address address) {
        if (!addressRepository.existsById(id)) {
            return null;
        }

        Address updatedAddress = addressRepository.getOne(id);
        BeanUtils.copyProperties(address, updatedAddress, "id");

        return addressRepository.save(updatedAddress);
    }

    @Override
    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }
}
