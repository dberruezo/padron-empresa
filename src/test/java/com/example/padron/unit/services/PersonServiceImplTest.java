package com.example.padron.unit.services;

import com.example.padron.models.Person;
import com.example.padron.repositories.IPersonRepository;
import com.example.padron.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class PersonServiceImplTest {
    @Autowired
    private PersonService personService;

    @MockBean
    private IPersonRepository personRepository;

    @Test
    public void whenPersonIsCorrectThenSave () {
        // Set-up
        Person person = new Person(
            23412,
            "María",
            "Del Rosario Sanchez",
            2729837212L,
            "argentina"
        );

        when(
            personRepository.save(any(Person.class))
        ).thenReturn(person);

        // Test
        Person newPerson = new Person(
            23412,
            "María",
            "Del Rosario Sanchez",
            2729837212L,
            "argentina"
        );

        Person savedPerson = personService.createPerson(newPerson);

        assertThat(savedPerson)
            .isEqualToComparingFieldByField(newPerson);
    }

    @Test
    public void whenPersonIsIncorrectThenDoNotSave () {
        when(
            personRepository.save(any(Person.class))
        ).thenThrow(IllegalArgumentException.class);

        Person badPerson = personService.createPerson(new Person());

        assertThat(badPerson).isNull();
    }

    @Test
    public void whenPersonExistsThenReturnPhone () {
        // Set-up
        Person mockPerson = new Person(
            23412,
            "María",
            "Del Rosario Sanchez",
            2729837212L,
            "argentina"
        );

        when(
            personRepository.findById(anyInt())
        ).thenReturn(Optional.of(mockPerson));

        // Test
        Person referencePerson = new Person(
            23412,
            "María",
            "Del Rosario Sanchez",
            2729837212L,
            "argentina"
        );

        Person readPerson = personService.readPerson(20);

        assertThat(readPerson)
            .isEqualToComparingFieldByField(referencePerson);
    }

    @Test
    public void whenPersonDoesNotExistThenReturnNull () {
        when(
            personRepository.findById(anyInt())
        ).thenReturn(Optional.empty());

        Person readPerson = personService.readPerson(9999);
        assertThat(readPerson).isNull();
    }

    @Test
    public void whenPersonExistsThenUpdateAndReturnPerson () {
        // Set-up
        Person mockPerson = new Person(
            23412,
            "María",
            "Del Rosario Sanchez",
            2729837212L,
            "argentina"
        );

        when(
            personRepository.existsById(anyInt())
        ).thenReturn(true);

        when(
            personRepository.getOne(anyInt())
        ).thenReturn(new Person());

        when(
            personRepository.save(any(Person.class))
        ).thenReturn(mockPerson);

        // Test
        Person updatedPerson = personService.updatePerson(20, mockPerson);

        assertThat(updatedPerson)
            .isEqualToComparingFieldByField(mockPerson);
    }

    @Test
    public void whenPersonDoesNotExistsThenReturnNull () {
        when(
            personRepository.existsById(anyInt())
        ).thenReturn(false);

        Person updatedPerson = personService.updatePerson(50, new Person());

        assertThat(updatedPerson).isNull();
    }

    @Test
    public void whenDeletingPersonThenReturnNothing () {
        doNothing().when(personRepository).deleteById(anyInt());

        personService.deletePerson(1);

        verify(
            personRepository, times(1)
        ).deleteById(1);
    }

    @Test
    public void whenGettingActivePersonnelThenReturnAllActivePersonnel () {
        List<Person> personnel = new ArrayList<>();

        personnel.add(
            new Person(
                23412,
                "María",
                "Del Rosario Sanchez",
                2729837212L,
                "argentina"
            )
        );

        personnel.add(
            new Person(
                63333,
                "Enrique",
                "De la Torre",
                2054367621L,
                "argentina"
            )
        );

        when(
            personRepository.findByActiveTrue()
        ).thenReturn(personnel);

        List<Person> activePersonnel = personService.getActivePersonnel();

        assertThat(activePersonnel).containsAll(personnel);
    }

    @Test
    public void whenGettingActivePersonnelByCuitThenReturnPersonnel () {
        List<Person> personnel = new ArrayList<>();

        personnel.add(
            new Person(
                63333,
                "Enrique",
                "De la Torre",
                2054367621L,
                "argentina"
            )
        );

        when(
            personRepository.findByCuitAndActiveTrue(anyLong())
        ).thenReturn(personnel);

        List<Person> foundPersonnel = personService.getPersonByCuit(2054367621L);

        assertThat(foundPersonnel).containsAll(personnel);
    }
}
