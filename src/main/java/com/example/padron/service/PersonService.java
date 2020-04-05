package com.example.padron.service;

import com.example.padron.models.Person;

import java.util.List;

public interface PersonService {
    Person createPerson(Person person);
    Person readPerson(int id);
    Person updatePerson(int id, Person person);
    void deletePerson(int id);
    List<Person> getActivePersonnel();
    List<Person> getPersonByCuit(Long cuit);
}
