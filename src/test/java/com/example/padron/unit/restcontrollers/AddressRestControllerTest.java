package com.example.padron.unit.restcontrollers;

import com.example.padron.models.Address;
import com.example.padron.rest.AddressRestController;
import com.example.padron.service.AddressService;
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

@WebMvcTest(AddressRestController.class)
public class AddressRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;

    @Test
    public void whenAddressExistsThenReturnAddress () throws Exception {
        // Set-up
        Address address = new Address(
            5,
            "Calle Falsa",
            123,
            "Springfield",
            "Massachusetts"
        );

        when(
            addressService.readAddress(anyInt())
        ).thenReturn(address);

        String expectedBody = objectMapper.writeValueAsString(address);

        // Test
        MvcResult result = mockMvc.perform(get("/address/{id}", 2)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isEqualTo(expectedBody);
    }

    @Test
    public void whenAddressDoesNotExistsThenReturnNotFound () throws Exception {
        when(
            addressService.readAddress(anyInt())
        ).thenReturn(null);

        mockMvc.perform(
            get("/address/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void whenAddressIsCorrectThenSaveAndReturn () throws Exception {
        // Set-up
        Address address = new Address();

        Address createdAddress = new Address(
            10,
            "Calle Falsa",
            123,
            "Springfield",
            "Massachusetts"
        );

        when(
            addressService.createAddress(any(Address.class))
        ).thenReturn(createdAddress);

        String expectedBody = objectMapper.writeValueAsString(createdAddress);

        // Test
        MvcResult result = mockMvc.perform(post("/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(address)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/address/10"))
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isEqualTo(expectedBody);
    }

    @Test
    public void whenAddressIsIncorrectThenReturnNotFound () throws Exception {
        // Set-up
        Address address = new Address(
            -20,
            "Calle Falsa",
            123,
            "Springfield",
            "Massachusetts"
        );

        when(
            addressService.createAddress(any(Address.class))
        ).thenReturn(null);

        // Test
        mockMvc.perform(post("/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(address)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void whenAddressUpdatesThenReturn () throws Exception {
        // Set-up
        Address updatedAddress = new Address(
            10,
            "Calle Falsa",
            123,
            "Alabama",
            "Texas"
        );

        String expectedBody = objectMapper.writeValueAsString(updatedAddress);

        when(
            addressService.updateAddress(anyInt(), any(Address.class))
        ).thenReturn(updatedAddress);

        // Test
        MvcResult result = mockMvc.perform(put("/address/{id}", 10)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedAddress)))
            .andExpect(status().isOk())
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).isEqualTo(expectedBody);
    }

    @Test
    public void whenAddressDoesNotUpdateThenReturnNotFound () throws Exception {
        // Set-up
        Address updatedAddress = new Address(
            11,
            "Calle Falsa",
            123,
            "Alabama",
            "Texas"
        );

        when(
            addressService.updateAddress(anyInt(), any(Address.class))
        ).thenReturn(null);

        // Test
        mockMvc.perform(put("/address/{id}", 10)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedAddress)))
            .andExpect(status().isNotFound())
            .andReturn();
    }

    @Test
    public void whenDeletingAddressThenReturnNoContent () throws Exception {
        mockMvc.perform(
            delete("/address/{id}", 20)
        ).andExpect(status().isNoContent());

        verify(
            addressService, times(1)
        ).deleteAddress(anyInt());
    }
}
