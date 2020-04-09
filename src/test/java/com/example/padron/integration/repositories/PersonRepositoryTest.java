package com.example.padron.integration.repositories;

import com.example.padron.models.Person;
import com.example.padron.models.Phone;
import com.example.padron.repositories.IPersonRepository;
import com.example.padron.repositories.IPhoneRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class PhoneRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Test
    public void whenFindByIdThenReturnPhone() {
        // Set-up
        Person person = new Person();
        person.setId(10);
        person.setName("John");
        person.setSurname("Doe");
        person.setCuit(20332212215L);
        person.setNationality("argentina");

        entityManager.persist(person);
        entityManager.flush();

        Phone phone = new Phone();
        phone.setAreaCode(11);
        phone.setNumber(1583726178L);
        phone.setPerson(person);

        person.setContactNumbers(new ArrayList<>());
        person.getContactNumbers().add(phone);

        entityManager.persist(phone);
        entityManager.flush();

        Integer phoneId = phone.getId();

        // Test
        Optional<Phone> query = phoneRepository
            .findById(phoneId);

        Phone foundPhone = null;

        if (query.isPresent()) {
            foundPhone = query.get();
        }

        if (foundPhone != null) {
            assertThat(foundPhone.getNumber())
                .isEqualTo(phone.getNumber());
        } else {
            Assert.fail("Phone should not be null.");
        }
    }
}
