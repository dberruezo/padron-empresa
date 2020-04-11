package com.example.padron.integration.repositories;

import com.example.padron.models.Person;
import com.example.padron.repositories.IPersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class PersonRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IPersonRepository personRepository;

    @Test
    public void whenFindByActiveThenReturnActivePersonnel() {
        Person person = new Person(
            10,
            "John",
            "Doe",
            20332212215L,
            "argentina"
        );

        Person nonActive = new Person(
            12414231,
            "Veronica",
            "Alvarez",
            2733982817L,
            "argentina"
        );
        nonActive.setActive(false);

        entityManager.persist(person);
        entityManager.flush();

        entityManager.persist(nonActive);
        entityManager.flush();

        List<Person> allPersonnel = personRepository.findAll();
        List<Person> activePersonnel = personRepository
            .findByActiveTrue();

        assertThat(allPersonnel).hasSize(3);
        assertThat(activePersonnel).hasSize(2);
    }

    @Test
    public void whenFindByCuitThenReturnActivePerson () {
        Person thirdActivePerson = new Person(
            5342232,
            "Soledad",
            "Morales",
            2731234726L,
            "argentina"
        );

        entityManager.persist(thirdActivePerson);
        entityManager.flush();

        List<Person> allPersonnel = personRepository.findAll();
        List<Person> personnelByCuit = personRepository
            .findByCuitAndActiveTrue(2731234726L);

        assertThat(allPersonnel).hasSize(2);
        assertThat(personnelByCuit).hasSize(1);
        assertThat(personnelByCuit).first()
            .isEqualToComparingFieldByField(thirdActivePerson);
    }
}
