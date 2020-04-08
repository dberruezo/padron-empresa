package com.example.padron.unit.restcontrollers;

import com.example.padron.models.Address;
import com.example.padron.models.Person;
import com.example.padron.rest.PersonRestController;
import com.example.padron.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonRestController.class)
public class PersonRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @Test
    public void whenPersonExistsThenReturnPerson () throws Exception {
        Person person = new Person(
            77765,
            "John",
            "Doe",
            20476372651L,
            "estadounidense"
        );

        when(
            personService.readPerson(anyInt())
        ).thenReturn(person);

        String expectedBody = objectMapper.writeValueAsString(person);

        // Test
        MvcResult result = mockMvc.perform(get("/personnel/{id}", 77765)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isEqualTo(expectedBody);
    }

    @Test
    public void whenPersonDoesNotExistsThenReturnNotFound () throws Exception {
        when(
            personService.readPerson(anyInt())
        ).thenReturn(null);

        mockMvc.perform(
            get("/personnel/{id}", 34)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void whenPersonIsCorrectThenSaveAndReturn () throws Exception {
        // Set-up
        Person person = new Person();

        Person createdPerson = new Person(
            77765,
            "John",
            "Doe",
            20476372651L,
            "estadounidense"
        );

        when(
            personService.createPerson(any(Person.class))
        ).thenReturn(createdPerson);

        String expectedBody = objectMapper.writeValueAsString(createdPerson);

        // Test
        MvcResult result = mockMvc.perform(post("/personnel")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(person)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/personnel/77765"))
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isEqualTo(expectedBody);
    }

    @Test
    public void whenPersonIsIncorrectThenReturnNotFound () throws Exception {
        // Set-up
        Person person = new Person(
            77765,
            "John",
            "Doe",
            20476372651L,
            "estadounidense"
        );

        when(
            personService.createPerson(any(Person.class))
        ).thenReturn(null);

        // Test
        mockMvc.perform(post("/personnel")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(person)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void whenPersonIsUpdatedThenReturnPerson () throws Exception {
        // Set-up
        Person updatedPerson = new Person(
            77765,
            "John",
            "Doe",
            20476372651L,
            "estadounidense"
        );

        String expectedBody = objectMapper.writeValueAsString(updatedPerson);

        when(
            personService.updatePerson(anyInt(), any(Person.class))
        ).thenReturn(updatedPerson);

        // Test
        MvcResult result = mockMvc.perform(put("/personnel/{id}", 77765)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedPerson)))
            .andExpect(status().isOk())
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isEqualTo(expectedBody);
    }

    @Test
    public void whenPersonDoesNotUpdateThenReturnNotFound () throws Exception {
        // Set-up
        Person updatedPerson = new Person(
            77765,
            "John",
            "Doe",
            20476372651L,
            "estadounidense"
        );

        when(
            personService.updatePerson(anyInt(), any(Person.class))
        ).thenReturn(null);

        // Test
        mockMvc.perform(put("/personnel/{id}", 77765)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedPerson)))
            .andExpect(status().isNotFound())
            .andReturn();
    }

    @Test
    public void whenPersonIsDeletedThenReturnNoContent () throws Exception {
        mockMvc.perform(
            delete("/personnel/{id}", 2053)
        ).andExpect(status().isNoContent());

        verify(
            personService, times(1)
        ).deletePerson(anyInt());
    }
}
