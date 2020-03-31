package com.example.padron.repositories;

import com.example.padron.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
}
