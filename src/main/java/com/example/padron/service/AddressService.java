package com.example.padron.service;

import com.example.padron.models.Address;

public interface AddressService {
    Address createAddress(Address address);
    Address readAddress(int id);
    Address updateAddress(int id, Address address);
    void deleteAddress(int id);
}
