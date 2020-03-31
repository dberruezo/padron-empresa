package com.example.padron.repositories;

import com.example.padron.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Integer>, PagingAndSortingRepository<Person, Integer> {
    List<Person> findByActiveTrue();
    Person findByCuit(Long cuit);
}
