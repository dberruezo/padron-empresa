package com.example.padron.unit.restcontrollers;

import com.example.padron.models.Phone;
import com.example.padron.rest.PhoneRestController;
import com.example.padron.service.PhoneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhoneRestController.class)
public class PhoneRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PhoneService phoneService;

    @Test
    public void whenPhoneIsCorrectThenSaveAndReturn () throws Exception {
        // Set-up
        Phone phone = new Phone();
        phone.setAreaCode(11);
        phone.setNumber(1533978200L);

        Phone createdPhone = new Phone(
            3, 11, 1533978200L
        );

        Mockito.when(
            phoneService.createPhone(any(Phone.class))
        ).thenReturn(createdPhone);

        String expectedBody = objectMapper.writeValueAsString(createdPhone);

        // Test
        MvcResult result = mockMvc.perform(post("/phone")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(phone)))
            .andExpect(status().isCreated())
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isEqualTo(expectedBody);
    }

    @Test
    public void whenSaveIsIncorrectThenReturnNotFound () throws Exception {
        // Set-up
        Phone phone = new Phone();
        phone.setAreaCode(11);
        phone.setNumber(1533978200L);

        Mockito.when(
            phoneService.createPhone(any(Phone.class))
        ).thenReturn(null);

        // Test
        mockMvc.perform(post("/phone")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(phone)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void whenPhoneExistsThenReturnPhone () throws Exception {
        // Set-up
        Phone phone = new Phone(
            1, 11, 1574638292L
        );

        Mockito.when(
            phoneService.readPhone(anyInt())
        ).thenReturn(phone);

        String expectedBody = objectMapper.writeValueAsString(phone);

        // Test
        MvcResult result = mockMvc.perform(get("/phone/2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isEqualTo(expectedBody);
    }

    @Test
    public void whenPhoneDoesNotExistThenReturnNotFound () throws Exception {
        Mockito.when(
            phoneService.readPhone(anyInt())
        ).thenReturn(null);

        mockMvc.perform(
            get("/phone/3")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void whenDeletingPhoneThenReturnNoContent () throws Exception {
        mockMvc.perform(
            delete("/phone/6")
        ).andExpect(status().isNoContent());

        Mockito.verify(
            phoneService, Mockito.times(1)
        ).deletePhone(anyInt());
    }
}
