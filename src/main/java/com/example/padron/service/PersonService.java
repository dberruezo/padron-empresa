package com.example.padron.service;

import com.example.padron.models.Person;

import java.util.List;

public interface PersonService {
    void createPerson(Person person);
    Person readPerson(int id);
    void updatePerson(Person person);
    void deletePerson(int id);
    List<Person> getActivePersonnel();
    List<Person> getPersonByCuit(Long cuit);
}
