package com.example.padron.service;

import com.example.padron.models.Person;
import com.example.padron.repositories.IPersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private IPersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {
        Person newPerson = null;

        try {
            newPerson = personRepository.save(person);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return newPerson;
    }

    @Override
    public Person readPerson(int id) {
        return personRepository
            .findById(id)
            .orElse(null);
    }

    @Override
    public Person updatePerson(int id, Person person) {
        if (!personRepository.existsById(id)) {
            return null;
        }

        Person updatedPerson = personRepository.getOne(id);
        BeanUtils.copyProperties(person, updatedPerson, "id");

        return personRepository.save(updatedPerson);
    }

    @Override
    public void deletePerson(int id) {
        personRepository.deleteById(id);
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
