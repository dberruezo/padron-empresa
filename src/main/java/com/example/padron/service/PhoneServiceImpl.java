package com.example.padron.service;

import com.example.padron.models.Phone;
import com.example.padron.repositories.IPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private IPhoneRepository phoneRepository;

    @Override
    public Phone createPhone(Phone phone) {
        Phone newPhone = null;

        try {
            newPhone = phoneRepository.save(phone);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return newPhone;
    }

    @Override
    public Phone readPhone(int id) {
        return phoneRepository
            .findById(id)
            .orElse(null);
    }

    @Override
    public void deletePhone(int id) {
        phoneRepository.deleteById(id);
    }
}
