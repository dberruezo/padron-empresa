package com.example.padron.unit.services;

import com.example.padron.models.Phone;
import com.example.padron.repositories.IPhoneRepository;
import com.example.padron.service.PhoneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PhoneServiceImplTest {
    @Autowired
    private PhoneService phoneService;

    @MockBean
    private IPhoneRepository phoneRepository;

    @Test
    public void whenPhoneIsCorrectThenSave () {
        // Set-up
        Phone mockPhone = new Phone(
            2, 11, 1598765423L
        );

        when(
            phoneRepository.save(any(Phone.class))
        ).thenReturn(mockPhone);

        // Test
        Phone newPhone = new Phone(
            2, 11, 1598765423L
        );

        Phone savedPhone = phoneService.createPhone(newPhone);

        assertThat(savedPhone)
            .isEqualToComparingFieldByField(newPhone);
    }

    @Test
    public void whenPhoneIsIncorrectThenDoNotSave () {
        when(
            phoneRepository.save(any(Phone.class))
        ).thenThrow(IllegalArgumentException.class);

        Phone badPhone = phoneService.createPhone(new Phone());

        assertThat(badPhone).isNull();
    }

    @Test
    public void whenPhoneExistsThenReturnPhone () {
        // Set-up
        Phone mockPhone = new Phone(
            1, 11, 1567876341L
        );

        when(
            phoneRepository.findById(1)
        ).thenReturn(Optional.of(mockPhone));

        // Test
        Phone referencePhone = new Phone(
            1, 11, 1567876341L
        );

        Phone readPhone = phoneService.readPhone(1);

        assertThat(readPhone)
            .isEqualToComparingFieldByField(referencePhone);
    }

    @Test
    public void whenPhoneDoesNotExistThenReturnNull () {
        when(
            phoneRepository.findById(anyInt())
        ).thenReturn(Optional.empty());

        Phone readPhone = phoneService.readPhone(9999);
        assertThat(readPhone).isNull();
    }

    @Test
    public void whenDeletingPhoneThenReturnNothing () {
        doNothing().when(phoneRepository).deleteById(anyInt());

        phoneService.deletePhone(1);

        verify(
            phoneRepository, times(1)
        ).deleteById(1);
    }
}
