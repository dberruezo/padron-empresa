package com.example.padron.service;

import com.example.padron.models.Phone;

public interface PhoneService {
    void createPhone(Phone phone);
    Phone readPhone(int id);
    void deletePhone(int id);
}
