package com.example.padron.service;

import com.example.padron.models.Phone;
import com.example.padron.repositories.IPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private IPhoneRepository phoneRepository;

    @Override
    public void createPhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public Phone readPhone(int id) {
        return phoneRepository
            .findById(id)
            .orElse(null);
    }

    @Override
    public void deletePhone(int id) {
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
        }
    }
}
