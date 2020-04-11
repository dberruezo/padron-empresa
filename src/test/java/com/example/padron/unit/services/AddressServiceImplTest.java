package com.example.padron.unit.services;

import com.example.padron.models.Address;
import com.example.padron.repositories.IAddressRepository;
import com.example.padron.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressServiceImplTest {
    @Autowired
    private AddressService addressService;

    @MockBean
    private IAddressRepository addressRepository;

    @Test
    public void whenAddressIsCorrectThenSave () {
        // Set-up
        Address address = new Address(
            20,
            "Av. del Libertador",
            14638,
            "Acassuso",
            "Buenos Aires"
        );

        when(
            addressRepository.save(any(Address.class))
        ).thenReturn(address);

        // Test
        Address newAddress = new Address(
            20,
            "Av. del Libertador",
            14638,
            "Acassuso",
            "Buenos Aires"
        );

        Address savedAddress = addressService.createAddress(newAddress);

        assertThat(savedAddress)
            .isEqualToComparingFieldByField(newAddress);
    }

    @Test
    public void whenAddressIsIncorrectThenDoNotSave () {
        when(
            addressRepository.save(any(Address.class))
        ).thenThrow(IllegalArgumentException.class);

        Address badAddress = addressService.createAddress(new Address());

        assertThat(badAddress).isNull();
    }

    @Test
    public void whenAddressExistsThenReturnPhone () {
        // Set-up
        Address mockAddress = new Address(
            20,
            "Av. del Libertador",
            14638,
            "Acassuso",
            "Buenos Aires"
        );

        when(
            addressRepository.findById(anyInt())
        ).thenReturn(Optional.of(mockAddress));

        // Test
        Address referenceAddress = new Address(
            20,
            "Av. del Libertador",
            14638,
            "Acassuso",
            "Buenos Aires"
        );

        Address readAddress = addressService.readAddress(20);

        assertThat(readAddress)
            .isEqualToComparingFieldByField(referenceAddress);
    }

    @Test
    public void whenAddressDoesNotExistThenReturnNull () {
        when(
            addressRepository.findById(anyInt())
        ).thenReturn(Optional.empty());

        Address readAddress = addressService.readAddress(9999);
        assertThat(readAddress).isNull();
    }

    @Test
    public void whenAddressExistsThenUpdateAndReturnAddress () {
        // Set-up
        Address mockAddress = new Address(
            20,
            "Av. del Libertador",
            14638,
            "Acassuso",
            "Buenos Aires"
        );

        when(
            addressRepository.existsById(anyInt())
        ).thenReturn(true);

        when(
            addressRepository.getOne(anyInt())
        ).thenReturn(new Address());

        when(
            addressRepository.save(any(Address.class))
        ).thenReturn(mockAddress);

        // Test
        Address updatedAddress = addressService.updateAddress(20, mockAddress);

        assertThat(updatedAddress)
            .isEqualToComparingFieldByField(mockAddress);
    }

    @Test
    public void whenAddressDoesNotExistsThenReturnNull () {
        when(
            addressRepository.existsById(anyInt())
        ).thenReturn(false);

        Address updatedAddress = addressService.updateAddress(50, new Address());

        assertThat(updatedAddress).isNull();
    }

    @Test
    public void whenDeletingAddressThenReturnNothing () {
        doNothing().when(addressRepository).deleteById(anyInt());

        addressService.deleteAddress(1);

        verify(
            addressRepository, times(1)
        ).deleteById(1);
    }
}
