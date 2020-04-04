package com.example.padron.service;

import com.example.padron.models.Person;
import com.example.padron.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private IPersonRepository personRepository;

    @Override
    public void createPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person readPerson(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePerson(int id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }
    }

    @Override
    public List<Person> getActivePersonnel() {
        return personRepository.findByActiveTrue();
    }

    @Override
    public List<Person> getPersonByCuit(Long cuit) {
        return personRepository.findByCuitAndActiveTrue(cuit);
    }
}
