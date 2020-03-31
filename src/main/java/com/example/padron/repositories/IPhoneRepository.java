package com.example.padron.repositories;

import com.example.padron.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhoneRepository extends JpaRepository<Phone, Integer> {
}
