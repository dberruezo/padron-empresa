package com.example.padron.service;

import com.example.padron.models.Address;

public interface AddressService {
    Address createAddress(Address address);
    Address readAddress(int id);
    void updateAddress(Address address);
    void deleteAddress(int id);
}
